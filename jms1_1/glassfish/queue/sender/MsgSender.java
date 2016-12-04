import javax.jms.Connection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  MsgSender{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Destination q;  
  private static int n=3;
  
  public static void main(String[] args){
    try{
      Connection conn=cf.createConnection();
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer=session.createProducer(q);
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
          m.setText("Hello "+i);
          producer.send(m);
      }
      producer.send(session.createMessage()); 
      session.close();
      conn.close();
    }
    catch(Exception e){
      //System.out.println("JMSException : "+e.getMessage());
      e.printStackTrace();
    }
    System.out.println("Sender finished");
  }
}
