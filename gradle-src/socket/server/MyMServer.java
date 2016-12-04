package server.impl;
import iserver.IMyMServer;
import java.net.ServerSocket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyMServer implements IMyMServer{
  
  public ServerSocket getServerSocket(int port){
    ServerSocket serverSocket = null;
    try{
      serverSocket = new ServerSocket(port);
    }
    catch (IOException e) {
      System.err.println("Could not listen on port: "+port);
      System.err.println(e.getMessage());
      System.exit(1);
    }
    System.out.println("ServerSocket is ready ...");
    return serverSocket;
  }
  
  public void myAction(ServerSocket serverSocket){
    int NTHREADS=8192;
    ExecutorService exec=Executors.newFixedThreadPool(NTHREADS);
    AppThread appThread=new AppThread();
    while(true){
      try{
        exec.execute(appThread.action.service(serverSocket.accept()));
      }
      catch(IOException e){
        e.printStackTrace();
      }        
    }
  }  
}