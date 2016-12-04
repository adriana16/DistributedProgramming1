class MsgHelloT{
  public static void main(String[] args){
    int n=3;
    String queueName="MyQueue";
    if(args.length>0)
      queueName=args[0];
    if(args.length>1)
      n=Integer.parseInt(args[1]);
    MsgSenderT sender=new MsgSenderT(queueName,n);
    SyncMsgReceiverT receiver =new SyncMsgReceiverT(queueName);
    //AsyncMsgReceiverT receiver =new AsyncMsgReceiverT(queueName);
    sender.start();
    receiver.start();
  }
}
