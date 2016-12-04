package server.impl;
import server.*;
import org.junit.*;
import static org.junit.Assert.*;
import iserver.IMyMServer;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import server.Protocol;

public class TestMyMServer{
  private IMyMServer obj;
  private static int port=7999;  
  public static final long M=12;
  public static final long N=15;
  public static final long RESULT=3;
  
  @Before
  public void initializare(){
    obj=new MyMServer(); 
  }
  
  @Test 
  public void test(){  
    int port=8999;
    Object result=obj.getDatagramSocket(port);
    assertNotNull("Must not return a null response",result);
    assertEquals(DatagramSocket.class,result.getClass());
  }
  
  @Test 
  public void testMyAction(){
    long r=0;
    DatagramSocket ds=obj.getDatagramSocket(port);
    EmbeddedThread thread=new EmbeddedThread(ds);
    thread.start();
    try{
      DatagramSocket socket = new DatagramSocket(); 
      Protocol p=new Protocol(M,N);
      ByteArrayOutputStream baos=new ByteArrayOutputStream(256);
      ObjectOutputStream out=new ObjectOutputStream(baos);
      out.writeObject(p);
      byte[] bout=baos.toByteArray();
      InetAddress address=InetAddress.getByName("localhost");
      DatagramPacket packet=new DatagramPacket(bout,bout.length,address,port);
      socket.send(packet);
      byte[] bin=new byte[4048];
      packet=new DatagramPacket(bin,bin.length);
      socket.receive(packet);
      ByteArrayInputStream bais=new ByteArrayInputStream(bin);
      ObjectInputStream in=new ObjectInputStream(bais);
      p=(Protocol)in.readObject();
      r=p.x;
    } 
    catch(Exception e){
       System.err.println("Client comunication error : "+e.getMessage());
    }   
    assertEquals(r,RESULT);
  }
  
  public static void main(String[] args){
    org.junit.runner.JUnitCore.main("server.impl.TestMyMServer");
  }
  
  class EmbeddedThread extends Thread{
    DatagramSocket ds;
    
    EmbeddedThread(DatagramSocket ds){
      this.ds=ds;
    }
    
    public void run(){
      obj.myAction(ds);
    }
  }
}