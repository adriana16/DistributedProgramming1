import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import java.util.Scanner;

public class  MsgClientReceiver{
  private String topicResult,clientID,clientName;
  
  MsgClientReceiver(String clientID,String clientName){
    this.clientID=clientID;
    this.clientName=clientName;
    Scanner scanner=new Scanner(System.in);
    System.out.println("Introduceti 'Topic'-ul raspunsului");
    topicResult=scanner.next();
  }

  public void service(){
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic(topicResult); 
      JMSContext ctx=cf.createContext();
      ctx.setClientID(clientID);
      JMSConsumer consumer = ctx.createDurableConsumer(t,clientName);
      //JMSConsumer consumer = ctx.createConsumer(t);
       
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println("Cmmdc : "+m.getText());
          break;
        }
      }   
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
  }
  
  public static void main(String[] args){
    if(args.length<2){
      System.out.println("Usage:");
      System.out.println("java MsgSOAPClientReceiver clientID clientName");
      System.exit(0);
    }
    MsgClientReceiver client=new MsgClientReceiver(args[0],args[1]);
    client.service();
  }
}
