package server.impl;
import iserver.IMyMServer;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.util.concurrent.Future;

public class MyMServer implements IMyMServer{
  
  public AsynchronousServerSocketChannel getAsynchronousServerSocketChannel(int port){
    AsynchronousServerSocketChannel asynchronousServerSocketChannel=null;
    try{
      asynchronousServerSocketChannel=AsynchronousServerSocketChannel.open();
      InetSocketAddress isa=new InetSocketAddress(port);
      asynchronousServerSocketChannel.bind(isa);
    }
    catch(IOException e){
      System.out.println("AsynchronousServerSocketChannelError : "+e.getMessage());
      System.exit(0);
    }
    System.out.println("Server ready... "); 
    return asynchronousServerSocketChannel;
  }
  
  public void myAction(AsynchronousServerSocketChannel asynchronousServerSocketChannel){
    int NTHREADS=100;
    ExecutorService exec=Executors.newFixedThreadPool(NTHREADS);
    AppThread appThread=new AppThread();
    while(true){
      try{
        Future<AsynchronousSocketChannel> future=asynchronousServerSocketChannel.accept();
        while(!future.isDone());
        exec.execute(appThread.action.service(future.get()));
      }
      catch(Exception e){
        System.err.println("MyActionException : "+e.getMessage());
      }        
    }
  }
}
