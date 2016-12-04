import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.jms.JMSProducer;

public class  MsgCmmdcServer{
 
  public static void main(String[] args){
    MsgCmmdcServer server=new MsgCmmdcServer();
    server.service();
  }
  
  private void service(){
    try{
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic("Cmmdc"); 
      JMSContext ctx=cf.createContext();
      JMSConsumer consumer = ctx.createSharedConsumer(t,"Cmmdc");
      JMSProducer producer = ctx.createProducer();
      while(true){ 
        Message msg=null;
        while((msg=consumer.receive())!=null){
          if(msg instanceof TextMessage){
            TextMessage tm=(TextMessage)msg;
            String s=tm.getText();
            String[] ss=s.split(" ");
            long m=Long.parseLong(ss[0]);
            long n=Long.parseLong(ss[1]);
            String topic=ss[2];
            long c=cmmdc(m,n);
            Topic t1=new com.sun.messaging.Topic(topic); 
            producer.send(t1,(new Long(c)).toString());
            System.out.println("Server sent "+c+" to "+topic);
          }  
        }  
      }        
      
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
  }
  
  private long cmmdc(long m,long n){
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
}
