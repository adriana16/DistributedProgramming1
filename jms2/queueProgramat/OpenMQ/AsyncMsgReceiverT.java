import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;

public class  AsyncMsgReceiverT extends Thread{
  String queueName;

  AsyncMsgReceiverT(String queueName){
    this.queueName=queueName;
  }

  public void run(){
    try{
      // Varianta Sun Message Queue
      com.sun.messaging.QueueConnectionFactory cf=new com.sun.messaging.QueueConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Queue q=new com.sun.messaging.Queue(queueName); 
      JMSContext ctx=cf.createContext();
      JMSConsumer consumer = ctx.createConsumer(q);
    
      TextListener textListener=new TextListener();
      consumer.setMessageListener(textListener);
      textListener.run();
      ctx.close();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    System.out.println("Consumer finished");
    //System.exit(0);
  }
}
