package server.impl;
import server.impl.MyMServer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.net.ServerSocket;
import iserver.IMyMServer;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

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
    Object result=obj.getServerSocket(port);
    assertNotNull("Must not return a null response",result);
    assertEquals(ServerSocket.class,result.getClass());
  }
  
  @Test 
  public void testMyAction(){
    long r=0;
    ServerSocket ss=obj.getServerSocket(port);
    EmbeddedThread thread=new EmbeddedThread(ss);
    thread.start();
    try(Socket cmmdcSocket = new Socket("localhost",port); 
      DataInputStream in=new DataInputStream(cmmdcSocket.getInputStream());
      DataOutputStream out=new DataOutputStream(cmmdcSocket.getOutputStream())){
      out.writeLong(M);
      out.writeLong(N);
      r=in.readLong();
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
    ServerSocket ss;
    
    EmbeddedThread(ServerSocket ss){
      this.ss=ss;
    }
    
    public void run(){
      obj.myAction(ss);
    }
  }
}