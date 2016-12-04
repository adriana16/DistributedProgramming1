import javax.jms.QueueConnectionFactory;
//import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties; 
import java.io.IOException;

public class  SyncMsgReceiverT extends Thread{
  final String CONNECTION_JNDI_NAME = "ConnectionFactory";
  String QUEUE_JNDI_NAME = "queue";
  private InitialContext ctx;
  
  SyncMsgReceiverT(String queueName){
    QUEUE_JNDI_NAME=queueName;
  }
  
  public void run(){
    try{
      setupJNDI();
      // lookup the connection factory
      QueueConnectionFactory cf=(QueueConnectionFactory)ctx.lookup(CONNECTION_JNDI_NAME);
      //Destination q=(Destination)ctx.lookup(QUEUE_JNDI_NAME);
      Queue q=(Queue)ctx.lookup(QUEUE_JNDI_NAME);
      closeJNDI();
      
      JMSContext jmsctx=cf.createContext();
      JMSConsumer consumer = jmsctx.createConsumer(q);
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(m.getText());
        }
        else 
          break;     
      }
      jmsctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Consumer finished");
  }
  
  private void setupJNDI(){
    // Set the properties ...
    Properties properties = new Properties();
    try{
      properties.load(this.getClass().getResourceAsStream("jndi.properties")); 
    }
    catch(IOException e){
      System.out.println("JNDI-PropertiesError : "+e.getMessage());
    }
    // Create the initial context
    try{
      ctx = new InitialContext(properties);
    }
    catch (NamingException e){
      System.err.println("Error Setting up JNDI Context:" + e);
    }
  }

  /** Close the JNDI Context to keep everything happy. */
  private void closeJNDI(){
    try{
      ctx.close();
    }
    catch (NamingException e){
      System.err.println("Unable to close JNDI Context : " + e);
    }
  }
  /*
  private Object lookupJNDI(String name){
    try{
      return ctx.lookup(name);
    }
    catch (NamingException e){
      System.err.println("Error looking up '" + name + "' in JNDI Context:" + e);
    }
    return null;
  } 
  */
}
