package server;
import java.net.DatagramSocket;
import server.impl.MyMServer;
import iserver.IMyMServer;

public class AppServer{
  public static void main(String[] args){
    int port=7999;
    if(args.length>0)
      port=Integer.parseInt(args[0]);
    IMyMServer myMServer=new MyMServer();
    DatagramSocket datagramSocket=myMServer.getDatagramSocket(port);
    myMServer.myAction(datagramSocket);
  }
}