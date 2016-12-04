package receiver;
//import javax.jms.TopicConnectionFactory;
import javax.jms.Topic;
import javax.jms.Message;
import javax.jms.TextMessage; 
import java.util.Scanner;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;

public class  CmmdcReceiverClient{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("Introduceti 'Topic'-ul raspunsului");
    String topicResult=scanner.next();
    System.out.println("Introduceti 'ClientID'");
    String clientID=scanner.next();
    System.out.println("Introduceti 'ClientName'");
    String clientName=scanner.next();
    try{
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      Topic t=new com.sun.messaging.Topic(topicResult); 
      JMSContext ctx=cf.createContext();
      ctx.setClientID(clientID);
      JMSConsumer consumer = ctx.createDurableConsumer(t,clientName);
       
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
    System.out.println("Publisher finished");
    System.exit(0);
  }
}
