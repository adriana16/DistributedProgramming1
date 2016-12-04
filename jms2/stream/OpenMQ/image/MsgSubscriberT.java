import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.jms.StreamMessage;
import javax.jms.BytesMessage;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class  MsgSubscriberT extends Thread{
  String subiect;
  String clientID;
  String clientName;
  
  MsgSubscriberT(String subiect,String clientID, String clientName){
    this.subiect=subiect;
    this.clientID=clientID;
    this.clientName=clientName;
  }
  
  public void run(){
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic(subiect); 
      JMSContext ctx=cf.createContext();
      ctx.setClientID(clientID);
      JMSConsumer consumer = ctx.createDurableConsumer(t,clientName);
      //JMSConsumer consumer = ctx.createConsumer(t);
      
      Message msg=null;
      while((msg=consumer.receive())!=null){
        //if(msg instanceof BytesMessage){       
        //  BytesMessage m=(BytesMessage)msg;
        if(msg instanceof StreamMessage){       
          StreamMessage m=(StreamMessage)msg;
          m.reset();
          //byte[] b=new byte[100000];
          //int size=m.readBytes(b);
          byte[] bytes=(byte[])m.readObject();
          System.out.println(clientName+" received : "+bytes.length);
          InputStream in = new ByteArrayInputStream(bytes);
          BufferedImage bi = ImageIO.read(in);
          ShowImage showImage=new ShowImage(bi);
          showImage.show();
          break;
        }
      }      
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
  }
}
