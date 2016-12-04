import javax.jms.MessageListener;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Message;

public class  TextListener implements MessageListener{
  private boolean sfarsit=false;
  
  TextListener(){
    sfarsit=false;
  }
  
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
    }
  }
  
  public void run(){
    try{Thread.sleep(500);}catch(InterruptedException e){}
    while(!sfarsit){
      try{Thread.sleep(1);}catch(InterruptedException e){}
    };
	}
}
