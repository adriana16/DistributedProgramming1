import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;

public class  MsgSubscriberT extends Thread{
  String subiect;
  String clientID;
  String clientName;
  
  MsgSubscriberT(String subiect,String clientID, String clientName){
    this.subiect=subiect;
    this.clientID=clientID;
    this.clientName=clientName;
  }
  
  public void run(){
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic(subiect); 
      JMSContext ctx=cf.createContext();
      //ctx.setClientID(clientID);
      //JMSConsumer consumer = ctx.createDurableConsumer(t,clientName);
      JMSConsumer consumer = ctx.createConsumer(t);
       
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
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
  }
}
