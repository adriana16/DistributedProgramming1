import javax.jms.TopicConnection;
import javax.jms.Session;
import javax.jms.TopicSession;
import javax.jms.Destination;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.jms.TextMessage;
import javax.jms.Message;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQTopic;
import org.apache.qpid.client.AMQConnection;

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
      TopicConnection conn =
            new AMQConnection("amqp://guest:guest@jms.clientID="+clientID+"/?brokerlist='tcp://localhost:5672'");
      
      Topic t = new AMQAnyDestination("ADDR:amq.topic/"+subiect+";{create: always}");
      
      
      //conn.setClientID(clientID);
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);     
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
  System.out.println("Subscriber "+clientName+" finished");
  }
}
