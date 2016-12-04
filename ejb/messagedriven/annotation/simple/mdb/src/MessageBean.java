package mdb; 
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(mappedName="myQueue")
public class MessageBean implements MessageListener {
  private static final Logger logger=Logger.getLogger(MessageBean.class.getName()); 
 
  public void onMessage(Message msg) {
    TextMessage txtMsg = null;
    try {
      if(msg instanceof TextMessage){
        txtMsg = (TextMessage) msg;
        logger.info("MESSAGE_BEAN: " + txtMsg.getText( ));
        System.out.println (txtMsg.getText());
      }  
    } 
    catch (Throwable th) {
      th.printStackTrace();
    }
  }
 
}