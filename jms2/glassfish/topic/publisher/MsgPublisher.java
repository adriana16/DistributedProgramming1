//import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.Topic;
//import javax.jms.TopicSession;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
//import javax.jms.Message;
//import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  MsgPublisher{
  @Resource(lookup="myTopicConnectionFactory")
  private static TopicConnectionFactory cf;
  @Resource(lookup="myTopic")
  private static Topic t;  
  
  private static int n=3;
  
  public static void main(String[] args){
    try{
      JMSContext jmsctx=cf.createContext();
      JMSProducer producer=jmsctx.createProducer(); 
      for(int i=0;i<n;i++){
        producer.send(t,"Despre JMS"+" "+i);
      }
      producer.send(t,jmsctx.createMessage());    
      jmsctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
  }
}
