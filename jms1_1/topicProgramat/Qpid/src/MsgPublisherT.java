import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

public class  MsgPublisherT extends Thread{
  int n;
  String subiect;
  int index;
  
  MsgPublisherT(String subiect,int n){
    super();
    this.n=n;
    this.subiect=subiect;
  }
  
  public void run(){
    try{
      Connection conn =
        new AMQConnection("amqp://guest:guest@test/?brokerlist='tcp://localhost:5672'");
      Destination t = new AMQAnyDestination("ADDR:amq.topic/"+subiect+";{create: always}");
      
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(t); 
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
        m.setText("Despre "+subiect+" "+i);
        producer.send(m);
      }
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
