import javax.jms.*;

public class  MsgSenderT extends Thread{
  int n;
  String queueName;
  
  MsgSenderT(String queueName,int n){
    this.queueName=queueName;
    this.n=n;
  }
  
  public void run(){
    try{
      // Varianta Oracle-Sun Message Queue
      //com.sun.messaging.QueueConnectionFactory cf=new com.sun.messaging.QueueConnectionFactory();
      
      // setProperty este metoda a clasei com.sun.messaging.QueueConnnectionFactory
      // ce implementeaza interfata QueueConnectionFactory
      // dar nu este a interfetei QueueConnectionFactory.
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      
      // Varianta Apache-MessageQueue
      
      org.apache.activemq.ActiveMQConnectionFactory cf=
         new org.apache.activemq.ActiveMQConnectionFactory("tcp://localhost:61616");
        
      Connection conn=cf.createConnection();
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
  
      //Queue q=new com.sun.messaging.Queue(queueName); 
      Destination q=session.createQueue(queueName);
      
      MessageProducer producer=session.createProducer(q);
    
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
          m.setText("Hello "+i);
          producer.send(m);
      }
      producer.send(session.createMessage()); 
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Sender finished");
  }
}
