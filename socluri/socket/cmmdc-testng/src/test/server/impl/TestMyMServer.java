package server.impl;
import server.impl.MyMServer;
import org.testng.annotations.*;
import org.testng.Assert;
import java.net.ServerSocket;
import iserver.IMyMServer;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TestMyMServer{
  private Object[] objs=new Object[1]; 
  private static int port=7999;
  public static final long M=12;
  public static final long N=15;
  public static final long RESULT=3;
  
  @Factory
  public Object[] initializare(){
    objs[0]=new MyMServer(); 
    return objs;
  }
  
  @Test (groups={"base"})
  public void testServerExistence(){ 
    int port=8999;  
    Object result=((IMyMServer)objs[0]).getServerSocket(port);
    Assert.assertNotNull(result);
    Assert.assertEquals(ServerSocket.class,result.getClass());
  }
  
  @Test (groups={"base"})
  public void testMyAction(){
    long r=0;
    ServerSocket ss=((IMyMServer)objs[0]).getServerSocket(port);
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
    Assert.assertEquals(r,RESULT);
  }
  
  class EmbeddedThread extends Thread{
    ServerSocket ss;
    
    EmbeddedThread(ServerSocket ss){
      this.ss=ss;
    }
    
    public void run(){
      ((IMyMServer)objs[0]).myAction(ss);
    }
  }
}