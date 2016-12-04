package server.impl;
import iserver.IMyMServer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class MyMServer implements IMyMServer{
  public ServerSocketChannel getServerSocketChannel(int port){
    ServerSocketChannel serverSocketChannel=null;
    try{
      serverSocketChannel = ServerSocketChannel.open();
      InetSocketAddress isa=new InetSocketAddress(port);
      ServerSocket ss=serverSocketChannel.socket();
      ss.bind(isa);
    }
    catch(IOException e){
      e.printStackTrace();
    }
    System.out.println("Server ready... "); 
    return serverSocketChannel;
  }
  
  public void myAction(ServerSocketChannel serverSocketChannel){
    int NTHREADS=100;
    AppThread appThread=new AppThread();
    ExecutorService exec=Executors.newFixedThreadPool(NTHREADS);
    while(true){
      try{
        exec.execute(appThread.action.service(serverSocketChannel.accept()));
      }
      catch(IOException e){
        e.printStackTrace();
      }        
    }
  }
}
