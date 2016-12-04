import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;

public class  MsgSubscriberT extends Thread{
  String subiect;
  String clientID;
  String clientName;
  String sharedSubscriptionName;
  com.sun.messaging.TopicConnectionFactory cf;
  
  MsgSubscriberT(com.sun.messaging.TopicConnectionFactory cf,
    String subiect,String sharedSubscriptionName,String clientName){
    this.cf=cf;
    this.subiect=subiect;
    this.sharedSubscriptionName=sharedSubscriptionName;
    this.clientName=clientName;
  }  
  
  public void run(){
    try{
      Topic t=new com.sun.messaging.Topic(subiect); 
      JMSContext ctx=cf.createContext();
      JMSConsumer consumer = ctx.createSharedConsumer(t,sharedSubscriptionName);
       
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(clientName+" received : "+m.getText());
        }
        else 
          break;
      }   
      ctx.close();
    }
    catch(Exception e){
      System.out.println("MsgSubscriber : "+e.getMessage());
    }
    System.out.println("Subscriber "+clientName+" finished");
  }
}
