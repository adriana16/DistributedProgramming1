import javax.jms.*;

public class  MsgPublisherT extends Thread{
  int n;
  String subiect;
  
  MsgPublisherT(String subiect,int n){
    super();
    this.n=n;
    this.subiect=subiect;
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
      conn.start();
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      
      //Topic t=new com.sun.messaging.Topic(subiect);
      //TopicPublisher publisher=session.createPublisher(destination);
      
      Destination t = session.createTopic(subiect);
      MessageProducer producer = session.createProducer(t); 
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
        m.setText("Despre "+subiect+" "+i);
        producer.send(m);
        //publisher.publish(m);
      }
      //publisher.publish(session.createMessage());
      producer.send(session.createMessage());
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
  }
}
