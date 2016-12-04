package mdb; 
import javax.ejb.MessageDriven;
//import javax.jms.TopicConnectionFactory;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageListener;
import javax.jms.JMSException;
import java.util.logging.Logger; 
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

@MessageDriven(mappedName="myTopic")
public class CmmdcMsgBean implements MessageListener{
  private static final Logger logger=Logger.getLogger(CmmdcMsgBean.class.getName()); 

  public void onMessage(Message msg) {
    TextMessage txtMsg = null;
    try{
      if(msg instanceof TextMessage){
        txtMsg = (TextMessage)msg;
        logger.info("MESSAGE : " + txtMsg.getText( ));   
        String[] st=txtMsg.getText().split(" ");
        String sm=st[0];
        String sn=st[1];
        String topic=st[2];
        //System.out.println(sm+":"+sn+":"+topic);
        long a=Long.parseLong(sm);
        long b=Long.parseLong(sn);
        long c=cmmdc(a,b);
        com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
        JMSContext ctx=cf.createContext();
        JMSProducer producer = ctx.createProducer();
        Topic topicDest=new com.sun.messaging.Topic(topic); 
        producer.send(topicDest,(new Long(c)).toString());
      }  
    } 
    catch (JMSException e) {
      e.printStackTrace( );
    } 
    catch (Throwable th) {
      th.printStackTrace();
    }
  }
  
  private long cmmdc(long m, long n){
    long r,c;
    do{
      c=n;
      r=m%n;
      m=n;
      n=r;
    }while(r!=0);
    return c;
  }   
}