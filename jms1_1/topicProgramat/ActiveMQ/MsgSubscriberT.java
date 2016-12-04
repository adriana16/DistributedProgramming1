import javax.jms.*;

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
      // Varianta Oracle-Sun Message Queue
      //com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      
      // Varianta Apache-MessageQueue
      
      org.apache.activemq.ActiveMQConnectionFactory cf=new org.apache.activemq.ActiveMQConnectionFactory("tcp://localhost:61616");
      
      TopicConnection conn=cf.createTopicConnection();
      //necesar doar ptr. DurableSubscriber
      conn.setClientID(clientID);
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      //Topic t=new com.sun.messaging.Topic(subiect); 
      Destination t = session.createTopic(subiect);
      
      //MessageConsumer consumer=session.createConsumer(t);
      TopicSubscriber consumer=session.createDurableSubscriber((Topic)t,clientName);
  
      conn.start();
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(clientName+" received : "+m.getText());
        }
        else 
          break;
      }   
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
  }
}
