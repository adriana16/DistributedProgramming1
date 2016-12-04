import javax.jms.Connection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  SyncMsgReceiver{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Destination q;
  
  public static void main(String[] args){
    try{
      Thread.sleep(2000);
      Connection conn=cf.createConnection();
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);  
      MessageConsumer consumer=session.createConsumer(q);
      conn.start();
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(m.getText());
        }
        else{ 
          break;
        }
      }
      session.close();
      conn.close(); 
    }
    catch(Exception e){
      //System.out.println("JMSException : "+e.getMessage());
      e.printStackTrace();
    }
    System.out.println("Consumer finished");
  }
}
