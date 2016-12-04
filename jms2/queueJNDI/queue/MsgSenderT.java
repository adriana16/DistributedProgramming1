import javax.jms.QueueConnectionFactory;
import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties; 
import java.io.IOException;

public class  MsgSenderT extends Thread{
  final String CONNECTION_JNDI_NAME = "ConnectionFactory";
  String QUEUE_JNDI_NAME = "queue";
  private InitialContext ctx;
  private int n;
  
  MsgSenderT(String queueName,int n){
    QUEUE_JNDI_NAME=queueName;
    this.n=n;
  }
  
  public void run(){
    try{
      setupJNDI();
      // lookup the connection factory
      QueueConnectionFactory cf=(QueueConnectionFactory)ctx.lookup(CONNECTION_JNDI_NAME);
      Queue q=(Queue)ctx.lookup(QUEUE_JNDI_NAME);
      closeJNDI();
      
      JMSContext jmsctx=cf.createContext();
      JMSProducer producer=jmsctx.createProducer();    
      for(int i=0;i<n;i++){
          producer.send(q,"Hello "+i);
      }
      producer.send(q,jmsctx.createMessage()); 
      jmsctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Sender finished");
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
