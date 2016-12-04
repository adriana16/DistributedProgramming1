package mdb; 
import javax.jms.QueueConnectionFactory;
import javax.jms.Destination;
import javax.annotation.Resource; 
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

public class  MessageClient{
  @Resource(lookup="myQueueConnectionFactory")
  private static QueueConnectionFactory cf;
  @Resource(lookup="myQueue")
  private static Destination q;

  public static void main(String[] args){
    try{
      JMSContext ctx=cf.createContext();
      JMSProducer producer=ctx.createProducer();   
      for(int i=0;i<5;i++){
          producer.send(q,"Hello "+i);
      }
      producer.send(q,ctx.createMessage()); 
      ctx.close();
    } 
    catch (Exception e) {
      e.printStackTrace ();
    }
  }
}
  
