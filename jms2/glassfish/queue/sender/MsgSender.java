//import javax.jms.Connection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
//import javax.jms.Message;
//import javax.jms.TextMessage;
import javax.annotation.Resource; 

public class  MsgSender{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Queue q;  
  private static int n=3;
  
  public static void main(String[] args){
    try{
      JMSContext jmsctx=cf.createContext();
      JMSProducer producer=jmsctx.createProducer();    
      for(int i=0;i<n;i++){
          producer.send(q,"Hello "+i);
      }
      producer.send(q,jmsctx.createMessage()); 
      jmsctx.close();
    }
    catch(Exception e){
      //System.out.println("JMSException : "+e.getMessage());
      e.printStackTrace();
    }
    System.out.println("Sender finished");
  }
}
