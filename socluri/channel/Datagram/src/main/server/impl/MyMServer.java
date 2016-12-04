package server.impl;
import iserver.IMyMServer;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

public class MyMServer implements IMyMServer{

  interface ServerDatagramChannelAction{
    void service(DatagramChannel datagramChannel);
  }

  static ServerDatagramChannelAction action=(DatagramChannel datagramChannel)->{
    try{
      ByteBuffer bb = ByteBuffer.allocate(16);    
      //LongBuffer lb = bb.asLongBuffer();
      SocketAddress sa=datagramChannel.receive(bb);
      // Varianta 1
      long m=bb.getLong(0);
      long n=bb.getLong(8);
      // Varianta 2
      // long m=lb.get(0);
      // long n=lb.get(1);   
      App app=new App();
      long r=app.cmmdcService.cmmdc(m,n);
      bb.clear();
      // Varianta 1
      bb.putLong(0,r);
      // Varianta 2
      // lb.put(r);
      datagramChannel.send(bb,sa); 
    }
    catch(Exception e){
      e.printStackTrace();
    }       
  };  
  
  public DatagramChannel getDatagramChannel(int port){
    DatagramChannel datagramChannel=null;
    InetSocketAddress isa=new InetSocketAddress(port);
    try{
      datagramChannel = DatagramChannel.open();     
      DatagramSocket datagramSocket=datagramChannel.socket();
      datagramSocket.bind(isa);
    }
    catch(IOException e){
      System.out.println("DatagramChannelError : "+e.getMessage());
      System.exit(0);
    }
    System.out.println("Server ready... "); 
    return datagramChannel;
  }
  
  public void myAction(DatagramChannel datagramChannel){
    while(true){
      action.service(datagramChannel);
    }
  }
}
