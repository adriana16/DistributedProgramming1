import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.RingBuffer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server{
  private final static int RING_SIZE = 1024;
  private final static int NUM_EVENT_PROCESSORS = 2;
  private static RingBuffer<ValueEvent> ringBuffer=null;
  private static App app=new App(); 
  private static int n=1024;
  
  private final static EventHandler<ValueEvent> handler =
    new EventHandler<ValueEvent>() {
      public void onEvent(final ValueEvent event,
                          final long sequence,
                          final boolean endOfBatch) throws Exception{
        //handleCount++;
        Socket socket=event.getSocket();
        try(
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          DataInputStream in = new DataInputStream(socket.getInputStream())){  
          long m=in.readLong();
          long n=in.readLong();
          long r=app.cmmdc(m,n);
          out.writeLong(r);
          socket.close();
          out.close();
          in.close();
          //System.out.println("R: cmmdc("+m+" , "+n+") = "+r);
        }
        catch (IOException e) {
          System.err.println("EventHandlerException : "+e.getMessage());
          System.exit(1);
        }  
      }
    };

  public static void main(String[] args){
    System.out.println("Starting disruptor app.");

    ExecutorService executor = Executors.newFixedThreadPool(NUM_EVENT_PROCESSORS);
    Disruptor<ValueEvent> disruptor =
      new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, 32, executor);      
    disruptor.handleEventsWith(handler);
    RingBuffer<ValueEvent> ringBuffer = disruptor.start();
    
    int port=7999;
    boolean listening=true;
    long sequence;
    ValueEvent event;
    try(ServerSocket serverSocket=new ServerSocket(port)) {
      System.out.println("The server is listening on port "+port);
      while(listening){
        Socket socket=serverSocket.accept();
        sequence = ringBuffer.next();
        event = ringBuffer.get(sequence);
        event.setSocket(socket);
        ringBuffer.publish(sequence);      
      }  
    }
    catch(Exception e){
      System.err.println("DisruptorException : "+e.getMessage());
      System.exit(1);
    }       
    disruptor.shutdown();
    System.exit(0);
  }
}