package server;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;
import java.io.IOException;

public class AppServer{
  public static void main(String[] args){
    int port=7999;
    if(args.length>0)
      port=Integer.parseInt(args[0]);
    App app=new App();  
    ServerSocketChannel serverSocketChannel=null;
    try{
      serverSocketChannel = ServerSocketChannel.open();
      InetSocketAddress isa=new InetSocketAddress(port);
      ServerSocket ss=serverSocketChannel.socket();
      ss.bind(isa);
      serverSocketChannel.configureBlocking(false);
      System.out.println("Server ready... ");  
        
      Selector selector = Selector.open();
      SelectionKey serverkey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      ByteBuffer buffer = ByteBuffer.allocate(16);
      while(true){
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        for (Iterator i = keys.iterator(); i.hasNext();){
          SelectionKey key = (SelectionKey) i.next();
          i.remove();
          if (key == serverkey) {
            if (key.isAcceptable()){
              SocketChannel client = serverSocketChannel.accept();
              client.configureBlocking(false);
              SelectionKey clientkey = client.register(selector, SelectionKey.OP_READ);
              //clientkeyCmmdc.attach(new Integer(0));
            }
          } 
          else {
            if (key.isReadable()){             
              SocketChannel client = (SocketChannel) key.channel();          
              int bytesread = client.read(buffer);
              long m=buffer.getLong(0);
              long n=buffer.getLong(8);     
              long r=app.cmmdcService.cmmdc(m,n);
              buffer.clear();
              buffer.putLong(0,r);
              client.write(buffer);
              buffer.clear();
              client.close();
              key.cancel();             
            }
          }
        }
      }
    }  
    catch(Exception e){
      System.err.println("BufferOpException : "+e.getMessage());
    }
    finally{
      try{
        serverSocketChannel.close();
      }
      catch(Exception e){
        System.out.println("CloseException : "+e.getMessage());
      }    
    }
  }
}