//import javax.jms.Connection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  SyncMsgReceiver{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Queue q;
  
  public static void main(String[] args){
    try{
      JMSContext jmsctx=cf.createContext();
      JMSConsumer consumer = jmsctx.createConsumer(q);
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(m.getText());
        }
        else 
          break;     
      }
      jmsctx.close();
    }
    catch(Exception e){
      //System.out.println("JMSException : "+e.getMessage());
      e.printStackTrace();
    }
    System.out.println("Consumer finished");
  }
}
