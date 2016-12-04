import javax.jms.Connection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  AsyncMsgReceiver{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Destination q;
  
  public static void main(String[] args){
    try{
      Connection conn=cf.createConnection();
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
      MessageConsumer consumer=session.createConsumer(q);
      TextListener textListener=new TextListener();
      consumer.setMessageListener(textListener);
      conn.start();
      textListener.run();
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    System.out.println("Consumer finished");
  }
}
