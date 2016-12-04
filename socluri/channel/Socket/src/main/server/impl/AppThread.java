package server.impl;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;

public class AppThread extends Thread{
  interface ServerSocketChannelAction{
    Thread service(SocketChannel socket);
  }

  static ServerSocketChannelAction action=(SocketChannel socketChannel)->{
    return new Thread(()->{
      try{
        ByteBuffer bb = ByteBuffer.allocate(16);    
        //LongBuffer lb = bb.asLongBuffer();
        socketChannel.read(bb);
        // Varianta 1
        long m=bb.getLong(0);
        long n=bb.getLong(8);
        // Varianta 2
        // long m=lb.get(0);
        // long n=lb.get(1);
        App app=new App();
        long r=app.cmmdcService.cmmdc(m,n);
        bb.clear();
        // Varianta 1
        bb.putLong(0,r);
        // Varianta 2
        // lb.put(r);
        socketChannel.write(bb);
        socketChannel.close(); 
      }
      catch(IOException e){
        e.printStackTrace();
      }  
    });
  };  
}
