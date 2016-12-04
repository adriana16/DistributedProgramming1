//import javax.jms.Connection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
//import javax.jms.Message;
//import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  AsyncMsgReceiver{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Queue q;
  
  public static void main(String[] args){
    try{
      JMSContext jmsctx=cf.createContext();
      JMSConsumer consumer = jmsctx.createConsumer(q);
    
      TextListener textListener=new TextListener();
      consumer.setMessageListener(textListener);
      textListener.run();
      jmsctx.close();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    System.out.println("Consumer finished");
  }
}
