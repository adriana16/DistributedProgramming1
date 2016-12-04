package client;
import java.net.UnknownHostException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.io.IOException;

public class CmmdcClient {
  public static void main(String[] args) {
    String host="localhost";
    int port=7999;
    if (args.length>0)
      host=args[0];
    if (args.length>1)
      port=Integer.parseInt(args[1]);
    SocketChannel sc=null;  
    try{      
      InetSocketAddress isa=new InetSocketAddress(host,port); 
      sc=SocketChannel.open();
      sc.connect(isa);
    }
    catch (UnknownHostException e) {
      System.err.println("Server necunoscut: "+host+" "+e.getMessage());
      System.exit(1);
    }
    catch (IOException e) {
      System.err.println("Conectare imposibila la: "+
                host+" pe portul "+port+" "+e.getMessage());
      System.exit(1);
    }
    Scanner scanner=new Scanner(System.in);
    long m,n,r;
    System.out.println("m=");
    m=scanner.nextLong();
    System.out.println("n=");
    n=scanner.nextLong();
        
    ByteBuffer bb=ByteBuffer.allocate(16);
    // Varianta 1
    bb.putLong(0,m).putLong(8,n);
    // Varianta 2
    // LongBuffer lb=bb.asLongBuffer();
    // lb.put(0,m).put(1,n);
    try{
      sc.write(bb);
      bb.clear();
      sc.read(bb);
      // Varianta 1
      r=bb.getLong(0);
      // Varianta 2
      // r=lb.get(0);
      System.out.println("Cmmdc : "+r);
      sc.close();
    }
    catch(Exception e){
      System.err.println("Eroare de comunicatie"+e.getMessage());
    }
  }
}


