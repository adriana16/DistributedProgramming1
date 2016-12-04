package server.impl;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import java.io.IOException;

public class AppThread{
  interface ServerSocketAction{
    Thread service(AsynchronousSocketChannel asc);
  }

  static ServerSocketAction action=(AsynchronousSocketChannel asc)->{
    return new Thread(()->{
      try{  
        ByteBuffer bb = ByteBuffer.allocate(16);    
        //LongBuffer lb = bb.asLongBuffer();
        Future<Integer> fr=asc.read(bb);
        while(!fr.isDone());
        // Varianta 1
        long m=bb.getLong(0);
        long n=bb.getLong(8);
        // Varianta 2
        // long m=lb.get(0);
        // long n=lb.get(1);
        App app=new App();
        System.out.println(m+" <-> "+n);
        long r=app.cmmdcService.cmmdc(m,n);
        bb.clear();
        // Varianta 1
        bb.putLong(0,r);
        // Varianta 2
        // lb.put(r);
        Future<Integer> fw=asc.write(bb);
        while(!fw.isDone());
        asc.close(); 
      }
      catch(IOException e){
        e.printStackTrace();
      }  
    });
  }; 
}
