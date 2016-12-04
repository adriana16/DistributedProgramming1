import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;

public class  SyncMsgReceiverT extends Thread{
  String queueName;

  SyncMsgReceiverT(String queueName){
    this.queueName=queueName;
  }

  public void run(){
    try{
      // Varianta Oracle-Sun Message Queue
      com.sun.messaging.QueueConnectionFactory cf=new com.sun.messaging.QueueConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Queue q=new com.sun.messaging.Queue(queueName); 
      JMSContext ctx=cf.createContext();
      JMSConsumer consumer = ctx.createConsumer(q);
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(m.getText());
        }
        else 
          break;     
      }
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Consumer finished");
  }
}
