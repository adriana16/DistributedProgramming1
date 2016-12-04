package server.impl;
import server.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.nio.channels.DatagramChannel;
import iserver.IMyMServer;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

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
    Object result=obj.getDatagramChannel(port);
    assertNotNull("Must not return a null response",result);
    assertTrue(result instanceof DatagramChannel);
    System.out.println("result.getClass().toString() :");
    System.out.println(result.getClass().toString());
    System.out.println();
  }
  
  @Test
  public void test1(){ 
    DatagramChannel dc=obj.getDatagramChannel(9999);
    assertNotNull("Must not return a null DatagramChannel object \"dc\"",dc);
    System.out.println("dc.getClass().getSuperclass().toString() :");
    System.out.println(dc.getClass().getSuperclass().toString());
    System.out.println();
    System.out.println("dc.getClass().toString() :");
    System.out.println(dc.getClass().toString());
    System.out.println();
    assertEquals(dc.getClass().getSuperclass(),DatagramChannel.class);
  }
  
  @Test 
  public void testMyAction(){
    long r=0;
    DatagramChannel dc=obj.getDatagramChannel(port);
    EmbeddedThread thread=new EmbeddedThread(dc);
    thread.start();
    try{
      InetSocketAddress server=new InetSocketAddress("localhost",port);
      DatagramChannel dChannel=DatagramChannel.open();       
      DatagramSocket socket = dChannel.socket();
      InetSocketAddress isa = new InetSocketAddress(0);
      socket.bind(isa);
      ByteBuffer bb=ByteBuffer.allocate(16);
      bb.putLong(0,M).putLong(8,N);
      dChannel.send(bb,server);
      bb.clear();
      dChannel.receive(bb);
      r=bb.getLong(0);
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
    DatagramChannel dc;
    
    EmbeddedThread(DatagramChannel dc){
      this.dc=dc;
    }
    
    public void run(){
      obj.myAction(dc);
    }
  }
}