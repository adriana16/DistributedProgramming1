import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageConsumer;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

public class  AsyncMsgReceiverT extends Thread{
  String queueName;

  AsyncMsgReceiverT(String queueName){
    //super();
    this.queueName=queueName;
  }

  public void run(){
    try{
      Connection conn =
              new AMQConnection("amqp://guest:guest@test/?brokerlist='tcp://localhost:5672'");
      Destination q = new AMQAnyDestination("ADDR:amq.queue/"+queueName+";{create: always}");
        
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
      MessageConsumer consumer=session.createConsumer(q);
      TextListener textListener=new TextListener();
      conn.start();
      consumer.setMessageListener(textListener);   
      textListener.run();    
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    System.out.println("Consumer finished");
  }
}
