package server.impl;
import server.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.nio.channels.AsynchronousServerSocketChannel;
import iserver.IMyMServer;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

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
    Object result=obj.getAsynchronousServerSocketChannel(port);
    assertNotNull("Must not return a null response",result);
    //assertEquals(AsynchronousServerSocketChannel.class,result.getClass());
    assertTrue(result instanceof AsynchronousServerSocketChannel);
  }
  
  @Test 
  public void testMyAction(){
    long r=0;
    AsynchronousServerSocketChannel assc=
      obj.getAsynchronousServerSocketChannel(port);
    EmbeddedThread thread=new EmbeddedThread(assc);
    thread.start();
    try{
      InetSocketAddress isa=new InetSocketAddress("localhost",port); 
      AsynchronousSocketChannel asc=AsynchronousSocketChannel.open();  
      asc.connect(isa);
      // Setare empirica pentru conectare
      Thread.sleep(400);
      
      ByteBuffer bb=ByteBuffer.allocate(16);
      bb.putLong(0,M).putLong(8,N);
      Future<Integer> fw=asc.write(bb);
      while(!fw.isDone()){};
      //Integer x=fw.get();
      bb.clear();
      Future<Integer> fr=asc.read(bb);
      while(!fr.isDone()){};
      //x=fr.get();
      asc.close();
      r=bb.getLong(0);   
    } 
    catch(Exception e){
       e.printStackTrace();
    }   
    assertEquals(r,RESULT);
  }
  
  public static void main(String[] args){
    org.junit.runner.JUnitCore.main("server.impl.TestMyMServer");
  }
  
  class EmbeddedThread extends Thread{
    AsynchronousServerSocketChannel assc;
    
    EmbeddedThread(AsynchronousServerSocketChannel assc){
      this.assc=assc;
    }
    
    public void run(){
      obj.myAction(assc);
    }
  }
}