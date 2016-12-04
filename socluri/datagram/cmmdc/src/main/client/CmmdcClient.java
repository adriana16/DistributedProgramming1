package client;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import server.Protocol;
import java.util.Scanner;

public class CmmdcClient{  
  public static void main(String []args){    
    String hostServer="localhost";
    int portServer=7999;
    if(args.length>0)
       hostServer=args[0];
    if(args.length>1)
       portServer=Integer.parseInt(args[1]);
    try{
       DatagramSocket socket=new DatagramSocket();
       Protocol p=new Protocol(0,0);
       Scanner scanner=new Scanner(System.in);
       System.out.println("Introduceti primul numar: ");
       p.x=scanner.nextLong();
       System.out.println("Introduceti al doilea numar: ");
       p.y=scanner.nextLong();
       ByteArrayOutputStream baos=new ByteArrayOutputStream(256);
       ObjectOutputStream out=new ObjectOutputStream(baos);
       out.writeObject(p);
       byte[] bout=baos.toByteArray();
       InetAddress address=InetAddress.getByName(hostServer);
       DatagramPacket packet=new DatagramPacket(bout,bout.length,address,portServer);
       socket.send(packet);
       byte[] bin=new byte[4048];
       packet=new DatagramPacket(bin,bin.length);
       socket.receive(packet);
       ByteArrayInputStream bais=new ByteArrayInputStream(bin);
       ObjectInputStream in=new ObjectInputStream(bais);
       p=(Protocol)in.readObject();
       System.out.println("Cmmdc = "+p.x);  
       socket.close();
    }
    catch(Exception e){
       System.out.println(e.getMessage());
    } 
  }
}
   



