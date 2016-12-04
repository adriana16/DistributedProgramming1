import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

public class  MsgSenderT extends Thread{
  int n;
  String queueName;
  
  MsgSenderT(String queueName,int n){
    this.queueName=queueName;
    this.n=n;
  }
  
  public void run(){
    try{
      Connection conn =
            new AMQConnection("amqp://guest:guest@test/?brokerlist='tcp://localhost:5672'");
      
      Destination q = new AMQAnyDestination("ADDR:amq.queue/"+queueName+";{create: always}");
       
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
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
