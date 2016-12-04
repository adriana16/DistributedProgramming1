package streamingimage;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyMServer {
  static final int NTHREADS=1024;
  static ExecutorService exec=Executors.newFixedThreadPool(NTHREADS);
  
  public static void main(String[] args) throws IOException {
    int n=1024;
    if(args.length>0) n=Integer.parseInt(args[0]);
    int port=7998;
    boolean listening=true;
    try(ServerSocket serverSocket=new ServerSocket(port,n)) {
      System.out.println("The server is listening on port "+port);
      while(listening){
        AppThread obj=new AppThread(serverSocket.accept());
        exec.execute(obj);
      }
    }
    catch (IOException e) {
      System.err.println("Could not listen on port: "+port);
      System.err.println(e.getMessage());
    }    
    System.exit(0);
  }
}
