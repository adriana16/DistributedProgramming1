public class MsgPS{
  public static void main(String[] args){
    String subiect="JMS";
    String sharedSubscriptionName="ourSubscription";
    int n=3,noAbonati=3;
    if(args.length>0)
      subiect=args[0];
    if(args.length>1)
      n=Integer.parseInt(args[1]);
    MsgPublisherT publisher=new MsgPublisherT(subiect,n);
    ReceiversT abonati=new ReceiversT(noAbonati,subiect,sharedSubscriptionName);
    abonati.start();
    //MsgPublisherT publisher=new MsgPublisherT(subiect,n);
    /*
    for(int i=0;i<noAbonati;i++){
      abonat[i]=new MsgSubscriberT(subiect,"Client"+i,sharedSubscriptionName);
      abonat[i].start();
    }
   
    try{
      Thread.sleep(1000);
    }
    catch(InterruptedException e){}
     */
    publisher.start();
  }
}
