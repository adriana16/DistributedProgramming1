import javax.jms.*;
import java.io.*;
import java.util.StringTokenizer;

public class  MsgCmmdcServer{

  long cmmdc(long m,long n){
    long c,r;
    do{
      c=n;
      r=m % n;
      m=n;
      n=r;
    }
    while(r!=0);
    return c;
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
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      Destination tDate=session.createTopic("Cmmdc");
      TopicSubscriber consumer=session.createSubscriber((Topic)tDate);
      conn.start();
      TextMessage txtMsg=null;
      String sm="",sn="",topic="";
      while(true){
        txtMsg=(TextMessage)consumer.receive();
        String msg=txtMsg.getText();
        String[] st=msg.split(" ");
        sm=st[0];
        sn=st[1];
        topic=st[2];
        System.out.println(sm+":"+sn+":"+topic);
        long a=Long.parseLong(sm);
        long b=Long.parseLong(sn);
        long c=cmmdc(a,b);
        Destination tResult=session.createTopic(topic);
        MessageProducer producer=session.createProducer(tResult);
        txtMsg=session.createTextMessage((new Long(c)).toString());
        producer.send(txtMsg);
        System.out.println("Server sent "+c+" to "+topic);
      }
      //conn.close();
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
    System.out.println("Server finished");
  }

  public static void main(String[] args){
    MsgCmmdcServer server=new MsgCmmdcServer();
    server.service();
  }
}
