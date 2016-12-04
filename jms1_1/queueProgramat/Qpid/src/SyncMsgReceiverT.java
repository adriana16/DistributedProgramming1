import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.Message;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

public class  SyncMsgReceiverT extends Thread{
  String queueName;

  SyncMsgReceiverT(String queueName){
    this.queueName=queueName;
  }

  public void run(){
    try{
      Connection conn =
              new AMQConnection("amqp://guest:guest@test/?brokerlist='tcp://localhost:5672'");
      Destination q = new AMQAnyDestination("ADDR:amq.queue/"+queueName+";{create: always}");
       
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE); 
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
