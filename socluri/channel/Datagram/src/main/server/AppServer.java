package server;
import java.nio.channels.DatagramChannel;
import server.impl.MyMServer;
import iserver.IMyMServer;

public class AppServer{
  public static void main(String[] args){
    int port=7999;
    if(args.length>0)
      port=Integer.parseInt(args[0]);
    IMyMServer myMServer=new MyMServer();
    DatagramChannel datagramChannel = 
      myMServer.getDatagramChannel(port);
    myMServer.myAction(datagramChannel);
  }
}