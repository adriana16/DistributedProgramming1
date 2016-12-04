package mdb; 
import javax.jms.QueueConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext; 
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

public class  MessageClient{
  private static QueueConnectionFactory cf;
  private static Destination q;
  
  public static void main(String[] args){
    Context ctx=null;
    try{
      ctx=new InitialContext();   
      cf=(QueueConnectionFactory)ctx.lookup("myQueueConnectionFactory");
      q=(Destination)ctx.lookup("myQueue");
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
      System.exit(1);
    }    

    try {
      JMSContext jmsCtx=cf.createContext();
      JMSProducer producer=jmsCtx.createProducer();   
      for(int i=0;i<5;i++){
          producer.send(q,"Hello "+i);
      }
      producer.send(q,jmsCtx.createMessage()); 
      jmsCtx.close();
    } 
    catch (Exception e) {
      e.printStackTrace ();
    }
  }
}
  
