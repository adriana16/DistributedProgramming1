package server.impl;
import server.Protocol;
import iserver.IMyMServer;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyMServer implements IMyMServer{
  interface ServerDatagramAction{
    void service(DatagramSocket socket);
  }

  static ServerDatagramAction action=(DatagramSocket datagramSocket)->{
    DatagramPacket packet=null;
    Protocol p=null;  
    try{
      byte[] bin=new byte[4048];
      packet=new DatagramPacket(bin,bin.length);
      datagramSocket.receive(packet);
      ByteArrayInputStream bais=new ByteArrayInputStream(bin);
      ObjectInputStream in=new ObjectInputStream(bais);
      p=(Protocol)in.readObject();
      App app=new App();
      p.x=app.cmmdcService.cmmdc(p.x,p.y);
      p.y=0;
      ByteArrayOutputStream baos=new ByteArrayOutputStream(256);
      ObjectOutputStream out=new ObjectOutputStream(baos);
      out.writeObject(p);
      byte[] bout=baos.toByteArray();
      InetAddress address=packet.getAddress();
      int port=packet.getPort();
      packet=new DatagramPacket(bout,bout.length,address,port);
      datagramSocket.send(packet);
    }
    catch(Exception e){
      e.printStackTrace();
    }   
  };  
  
  public DatagramSocket getDatagramSocket(int port){
    DatagramSocket datagramSocket = null;
    try{
      datagramSocket = new DatagramSocket(port);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("DatagramSocket is ready ...");
    return datagramSocket;
  }
  
  public void myAction(DatagramSocket datagramSocket){ 
    while(true){
      action.service(datagramSocket);
    }  
  }
}
