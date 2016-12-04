import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  ReceiversT extends Thread{
  String subiect;
  String sharedSubscriptionName;
  int noAbonati;
  com.sun.messaging.TopicConnectionFactory cf;
  
  ReceiversT(int noAbonati,String subiect,String sharedSubscriptionName){
    this.subiect=subiect;
    this.noAbonati=noAbonati;
    this.sharedSubscriptionName=sharedSubscriptionName;
  }  
  
  public void run(){
    //MsgSubscriberT[] abonat=new MsgSubscriberT[noAbonati];
    ExecutorService exec=Executors.newFixedThreadPool(noAbonati);

    try{
      // Varianta Oracle-Sun Message Topic
      cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      
      for(int i=0;i<noAbonati;i++){
        String clientName="Client "+i;
        MsgSubscriberT t=new MsgSubscriberT(cf,subiect,sharedSubscriptionName,clientName);
        exec.execute(t);
      }
      /*
      for(int i=0;i<noAbonati;i++){
        abonat[i].join();
      }  
      */
      //while(!exec.isTerminated()){;};
      exec.shutdownNow();
    }
    catch(Exception e){
      System.out.println("Receivers : "+e.getMessage());
      
    }    
    //while(!exec.isTerminated()){;};
    System.out.println("Receivers finished");
  }
}
