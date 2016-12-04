import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

public class  MsgPublisherT extends Thread{
  int n;
  String subiect;
  
  MsgPublisherT(String subiect,int n){
    super();
    this.n=n;
    this.subiect=subiect;
  }
  
  public void run(){
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic(subiect); 
      JMSContext ctx=cf.createContext();
      JMSProducer producer=ctx.createProducer();
      for(int i=0;i<n;i++){
          producer.send(t,"Hello "+i);
      }
      producer.send(t,ctx.createMessage()); 
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
  }
}
