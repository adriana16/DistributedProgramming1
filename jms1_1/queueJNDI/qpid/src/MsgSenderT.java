import javax.jms.QueueConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Properties; 
import java.io.IOException;

import org.apache.qpid.client.AMQConnection;

public class  MsgSenderT extends Thread{
  final String CONNECTION_JNDI_NAME = "ConnectionFactory";
  String QUEUE_JNDI_NAME = "myqueue";
  private Context ctx;
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
      Connection conn=cf.createConnection();
      
      Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
      Destination q=(Destination)ctx.lookup(QUEUE_JNDI_NAME);
      closeJNDI();
      MessageProducer producer=session.createProducer(q);
    
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
          m.setText("Hello "+i);
          producer.send(m);
      }
      producer.send(session.createMessage()); 
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException Sender : "+e.getMessage());
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
