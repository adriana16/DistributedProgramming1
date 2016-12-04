package sender;
import javax.jms.TopicConnectionFactory;
import javax.jms.Destination;
import javax.jms.Topic;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.annotation.Resource; 
import java.util.Scanner;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSConsumer;

public class  CmmdcSenderClient{
  @Resource(lookup="myTopicConnectionFactory")
  private static TopicConnectionFactory cf;
  @Resource(lookup="myTopic")
  private static Destination t;  
  
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("Introduceti m :");
    long m=scanner.nextLong();
    String sm=new Long(m).toString();
    System.out.println("Introduceti n :");
    long n=scanner.nextLong();
    String sn=new Long(n).toString();
    System.out.println("Introduceti 'Topic'-ul raspunsului");
    String topicResult=scanner.next();
    System.out.println("Introduceti 'ClientID'");
    String clientID=scanner.next();
    System.out.println("Introduceti 'ClientName'");
    String clientName=scanner.next();
    String msg=sm+" "+sn+" "+topicResult;
    try{
      JMSContext ctx=cf.createContext();
      Topic t1=new com.sun.messaging.Topic(topicResult); 
      ctx.setClientID(clientID);
      JMSConsumer consumer = ctx.createDurableConsumer(t1,clientName);
      JMSProducer producer=ctx.createProducer();     
      producer.send(t,msg);     
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
    System.exit(0);
  }
}
