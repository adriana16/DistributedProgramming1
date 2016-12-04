class MsgPS{
  public static void main(String[] args){
    String subiect="JMS";
    int n=3,noAbonati=3;
    if(args.length>0)
      subiect=args[0];
    if(args.length>1)
      n=Integer.parseInt(args[1]);
    MsgSubscriberT[] abonat=new MsgSubscriberT[noAbonati];
    MsgPublisherT publisher=new MsgPublisherT(subiect,n);
    for(int i=0;i<noAbonati;i++){
      abonat[i]=new MsgSubscriberT(subiect,"ID"+i,"id"+i);
      abonat[i].start();
    }
    try{
      Thread.sleep(1000);
    }
    catch(InterruptedException e){}
    publisher.start();
  }
}
