import javax.jms.Queue;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

public class  MsgSenderT extends Thread{
  int n;
  String queueName;
  
  MsgSenderT(String queueName,int n){
    this.queueName=queueName;
    this.n=n;
  }
  
  public void run(){
    try{
      // Varianta Oracle-Sun Message Queue
      com.sun.messaging.ConnectionFactory cf=new com.sun.messaging.ConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Queue q=new com.sun.messaging.Queue(queueName); 
      JMSContext ctx=cf.createContext();
      JMSProducer producer=ctx.createProducer();
     
      for(int i=0;i<n;i++){
          producer.send(q,"Hello "+i);
      }
      producer.send(q,ctx.createMessage()); 
      ctx.close();
    }
    catch(Exception e){
      System.out.println("JMSException : "+e.getMessage());
    }
    System.out.println("Sender finished");
  }
}
