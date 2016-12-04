//import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.Topic;
//import javax.jms.TopicSession;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.jms.Message;
//import javax.jms.Topic;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  MsgSubscriber{
  @Resource(lookup="myTopicConnectionFactory")
  private static TopicConnectionFactory cf;
  @Resource(lookup="myTopic")
  private static Topic t;

  private static String clientID="xyzID";
  private static String clientName="xyz";
  
  public static void main(String[] args){
    try{
      JMSContext jmsctx=cf.createContext();
      jmsctx.setClientID(clientID);
      JMSConsumer consumer = jmsctx.createDurableConsumer(t,clientName);
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(clientName+" received : "+m.getText());
        }
        else 
          break;
      }   
      jmsctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
  }
  
}
