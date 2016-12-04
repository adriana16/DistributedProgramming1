package client;
import java.net.UnknownHostException;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.io.IOException;

public class CmmdcClient {
  public static void main(String[] args) throws IOException {
    String serverHost="localhost";
    int port=7999;
    if (args.length>0)
      serverHost=args[0];
    if (args.length>1)
      port=Integer.parseInt(args[1]);   
    InetSocketAddress server=null,isa=null;   
    try{
      server=new InetSocketAddress(InetAddress.getByName(serverHost),port);
    }
    catch(UnknownHostException e){
      System.out.println("Unknown host : "+e.getMessage());
      System.exit(1);
    }
    isa=new InetSocketAddress(0);
    DatagramChannel dc=null;
    try {
      dc=DatagramChannel.open();
      DatagramSocket socket = dc.socket();
      socket.bind(isa);
    }
    catch (IOException e) {
      System.err.println("Couldn't open the DatagramChannel "+ e.getMessage());
      System.exit(1);
    }
    long m,n,r;   
    Scanner scanner=new Scanner(System.in);
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
      dc.send(bb,server);
      bb.clear();
      dc.receive(bb);
      // Varianta 1
      r=bb.getLong(0);
      // Varianta 2
      // r=lb.get(0);      
      System.out.println("Cmmdc = "+r);
    }
    catch(Exception e){
      System.err.println("Client error : "+e.getMessage());
    }
    finally{
      if(dc!=null) dc.disconnect();
    }
  }
}


