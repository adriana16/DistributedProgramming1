import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.Destination;
import javax.jms.TopicSession;
import javax.jms.Session;
import javax.jms.TopicSubscriber;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  MsgSubscriber{
  @Resource(lookup="myTopicConnectionFactory")
  private static TopicConnectionFactory cf;
  @Resource(lookup="myTopic")
  private static Destination t;

  private static String clientID="xyzID";
  private static String clientName="xyz";
  
  public static void main(String[] args){
    try{
      TopicConnection conn=cf.createTopicConnection();
      //conn.setClientID(clientID);
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);               
      //TopicSubscriber consumer=session.createDurableSubscriber((Topic)t,clientName);
      TopicSubscriber consumer=session.createSubscriber((Topic)t);
  
      conn.start();
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(clientName+" received : "+m.getText());
        }
        else 
          break;
      }   
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
  }
  
}
