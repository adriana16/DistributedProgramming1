import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.Destination;
import javax.jms.TopicSession;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  MsgPublisher{
  @Resource(lookup="myTopicConnectionFactory")
  private static TopicConnectionFactory cf;
  @Resource(lookup="myTopic")
  private static Destination t;  
  
  private static int n=3;
  
  public static void main(String[] args){
    try{
      TopicConnection conn=cf.createTopicConnection();
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(t); 
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
        m.setText("Despre JMS"+" "+i);
        producer.send(m);
      }
      producer.send(session.createMessage());
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
  }
}
