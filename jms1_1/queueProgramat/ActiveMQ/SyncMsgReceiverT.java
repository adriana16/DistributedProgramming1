import javax.jms.*;

public class  SyncMsgReceiverT extends Thread{
String queueName;

SyncMsgReceiverT(String queueName){
  //super();
  this.queueName=queueName;
}

public void run(){
  try{
    // Varianta Oracle-Sun Message Queue
    //com.sun.messaging.QueueConnectionFactory cf=new com.sun.messaging.QueueConnectionFactory();
    // setProperty este metoda a clasei QueueConnnectionFactory
    // ce implementeaza interfata QueueConnectionFactory
    // dar nu este a interfetei.
    //cf.setProperty("imqBrokerHostName","host");
    //cf.setProperty("imqBrokerHostPort","7676");
    
    // Varianta Apache-MessageQueue
    
    org.apache.activemq.ActiveMQConnectionFactory cf=
         new org.apache.activemq.ActiveMQConnectionFactory("tcp://localhost:61616");
        
    Connection conn=cf.createConnection();
    Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
    
    //Queue q=new com.sun.messaging.Queue(queueName);
    Destination q=session.createQueue(queueName);
    
    MessageConsumer consumer=session.createConsumer(q);

    conn.start();
    Message msg=null;
    while((msg=consumer.receive())!=null){
      if(msg instanceof TextMessage){
        TextMessage m=(TextMessage)msg;
        System.out.println(m.getText());
      }
      else{ 
        break;
      }
    }
    session.close();
    conn.close(); 
  }
  catch(Exception e){
    System.out.println("JMSException : "+e.getMessage());
  }
  System.out.println("Consumer finished");
}
}
