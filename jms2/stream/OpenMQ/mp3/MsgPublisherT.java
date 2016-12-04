import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.StreamMessage;
import javax.jms.BytesMessage;
import javax.jms.JMSProducer;
import java.io.File;
import java.io.FileInputStream;

public class  MsgPublisherT extends Thread{
  String subiect;
  
  MsgPublisherT(String subiect){ 
    this.subiect=subiect;  
  }
  
  public void run(){
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic(subiect); 
      JMSContext ctx=cf.createContext();
      
      File f=new File("./mp3files/Katusha.mp3");
      int size=(int)f.length();
      byte[] bytes=new byte[size];
      FileInputStream fis=new FileInputStream(f);
      int i=fis.read(bytes,0,size);
     
      StreamMessage msg=ctx.createStreamMessage();
      msg.writeBytes(bytes);
      //BytesMessage msg=ctx.createBytesMessage();
      //msg.writeObject(bytes);
      
      JMSProducer producer=ctx.createProducer();
      producer.send(t,msg);
      
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
  }
}
