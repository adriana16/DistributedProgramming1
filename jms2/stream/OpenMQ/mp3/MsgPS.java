class MsgPS{
  public static void main(String[] args){
    String subiect="streaming";
    if(args.length>0)
      subiect=args[0];
    MsgSubscriberT abonat=new MsgSubscriberT(subiect,"myID","myName");
    MsgPublisherT publisher=new MsgPublisherT(subiect);
    abonat.start();
    
    try{
      Thread.sleep(1000);
    }
    catch(InterruptedException e){}
    
    publisher.start();
  }
}
