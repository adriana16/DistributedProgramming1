import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.jms.MessageListener;

public class  TextListener implements MessageListener{
	boolean sfarsit=false;
	public void onMessage(Message message){
		if(message instanceof TextMessage){
       TextMessage m=(TextMessage)message;
		   try{
			   String s=m.getText();
			   System.out.println(s);
		   }
		   catch(JMSException e){
			   System.out.println(e.getMessage());
		   }
		}
		else{
			sfarsit=true;
      System.exit(0);
    }  
	}

	public void run(){
		while(!sfarsit);
	}
}
