import javax.jms.TopicConnection;
import javax.jms.Session;
import javax.jms.TopicSession;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Message;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import javax.jms.TextMessage;
import java.util.Scanner;

public class  MsgClientSender{
  long m,n;
  String topicResult;
  String clientID,clientName;

  MsgClientSender(String clientID,String clientName){
    this.clientID=clientID;
    this.clientName=clientName;
    Scanner scanner=new Scanner(System.in);
    System.out.println("Introduceti m :");
    m=scanner.nextLong();
    System.out.println("Introduceti n :");
    n=scanner.nextLong();
    System.out.println("Introduceti 'Topic'-ul raspunsului");
    topicResult=scanner.next();
  }
  public void service(){
    try{
      /*
      org.apache.activemq.ActiveMQConnectionFactory cf=
         new org.apache.activemq.ActiveMQConnectionFactory("tcp://localhost:61616");
      */
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");  
      TopicConnection conn=cf.createTopicConnection();
      conn.setClientID(clientID);
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      Destination tResult=session.createTopic(topicResult);
      TopicSubscriber consumer=session.createDurableSubscriber((Topic)tResult,clientName);
      Destination t = session.createTopic("Cmmdc");
      MessageProducer producer = session.createProducer(t); 
      TextMessage txtMsg=session.createTextMessage();
      String mesaj=m+" "+n+" "+topicResult;
      txtMsg.setText(mesaj);
      producer.send(txtMsg);
      session.close();
      conn.close(); 
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
    System.out.println("Sender finished");
  }
    
  public static void main(String[] args){
    if(args.length<2){
       System.out.println("Usage:");
       System.out.println("java MsgClientSender clientID clientName");
       System.exit(0);
    }
    MsgClientSender client=new MsgClientSender(args[0],args[1]);
    client.service();
  }
}
