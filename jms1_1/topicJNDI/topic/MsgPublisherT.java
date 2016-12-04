import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.Session;
import javax.jms.TopicSession;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties; 
import java.io.IOException;

public class  MsgPublisherT extends Thread{
  final String CONNECTION_JNDI_NAME = "ConnectionFactory";
  String TOPIC_JNDI_NAME = "topic";
  private InitialContext ctx;
  private int n;
  
  MsgPublisherT(String subiect,int n){
    this.n=n;
    TOPIC_JNDI_NAME=subiect;
  }
  
  public void run(){
    try{
      setupJNDI();
      // lookup the connection factory
      TopicConnectionFactory cf=(TopicConnectionFactory)ctx.lookup(CONNECTION_JNDI_NAME);
      TopicConnection conn=cf.createTopicConnection();
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      Destination t=(Destination)ctx.lookup(TOPIC_JNDI_NAME);
      closeJNDI();
      
      MessageProducer producer = session.createProducer(t); 
      TextMessage m=session.createTextMessage();
      for(int i=0;i<n;i++){
        m.setText("Despre JMS"+" "+i);
        producer.send(m);
      }
      producer.send(session.createMessage());
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Publisher finished");
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
