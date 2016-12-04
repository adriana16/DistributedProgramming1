import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageConsumer;

public class  AsyncMsgReceiverT extends Thread{
String queueName;

AsyncMsgReceiverT(String queueName){
  //super();
  this.queueName=queueName;
}

public void run(){
  try{
    // Varianta Sun Message Queue
    com.sun.messaging.QueueConnectionFactory cf=new com.sun.messaging.QueueConnectionFactory();
    //cf.setProperty("imqBrokerHostName","host");
    //cf.setProperty("imqBrokerHostPort","7676");
    
    // Varianta Apache-MessageQueue
    /*
    org.apache.activemq.ActiveMQConnectionFactory cf=
         new org.apache.activemq.ActiveMQConnectionFactory("tcp://localhost:61616");
    */
    Connection conn=cf.createConnection();
    Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
    //Queue q=new com.sun.messaging.Queue(queueName);
    Destination q=session.createQueue(queueName); 
    
    MessageConsumer consumer=session.createConsumer(q);
    TextListener textListener=new TextListener();
    consumer.setMessageListener(textListener);
    conn.start();
    textListener.run();
        conn.close();
  }
  catch(Exception e){
    System.out.println(e.getMessage());
  }
  System.out.println("Consumer finished");
  }
}
