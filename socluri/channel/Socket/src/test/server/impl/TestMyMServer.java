package server.impl;
import server.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import iserver.IMyMServer;

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
    Object result=obj.getServerSocketChannel(port);
    assertNotNull("Must not return a null response",result);
    //assertEquals(ServerSocketChannel.class,result.getClass());
    assertTrue(result instanceof ServerSocketChannel);
  }
  
  @Test 
  public void testMyAction(){
    long r=0;
    ServerSocketChannel ssc=obj.getServerSocketChannel(port);
    EmbeddedThread thread=new EmbeddedThread(ssc);
    thread.start();
    try{
      SocketChannel sc=SocketChannel.open();  
      InetSocketAddress isa=new InetSocketAddress("localhost",port); 
      sc=SocketChannel.open();
      sc.connect(isa);
      ByteBuffer bb=ByteBuffer.allocate(16);
      bb.putLong(0,M).putLong(8,N);
      sc.write(bb);
      bb.clear();
      sc.read(bb);
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
    ServerSocketChannel ssc;
    
    EmbeddedThread(ServerSocketChannel ssc){
      this.ssc=ssc;
    }
    
    public void run(){
      obj.myAction(ssc);
    }
  }
}