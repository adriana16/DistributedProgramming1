import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.Session;
import javax.jms.TopicSession;
import javax.jms.Destination;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties; 
import java.io.IOException;

public class  MsgSubscriberT extends Thread{
  final String CONNECTION_JNDI_NAME = "ConnectionFactory";
  String TOPIC_JNDI_NAME = "topic";
  private InitialContext ctx;  
  private String clientID;
  private String clientName;
  
  MsgSubscriberT(String subiect,String clientID, String clientName){
    TOPIC_JNDI_NAME=subiect;
    this.clientID=clientID;
    this.clientName=clientName;
  }
  
  public void run(){
    try{
      setupJNDI();
      // lookup the connection factory
      TopicConnectionFactory cf=(TopicConnectionFactory)ctx.lookup(CONNECTION_JNDI_NAME);
      TopicConnection conn=cf.createTopicConnection();
      //conn.setClientID(clientID);
      TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);               
      Destination t=(Destination)ctx.lookup(TOPIC_JNDI_NAME);
      closeJNDI();
      //TopicSubscriber consumer=session.createDurableSubscriber((Topic)t,clientName);
      TopicSubscriber consumer=session.createSubscriber((Topic)t);
  
      conn.start();
      Message msg=null;
      while((msg=consumer.receive())!=null){
        if(msg instanceof TextMessage){
          TextMessage m=(TextMessage)msg;
          System.out.println(clientName+" received : "+m.getText());
        }
        else 
          break;
      }   
      session.close();
      conn.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
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
