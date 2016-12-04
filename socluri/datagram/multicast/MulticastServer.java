import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DateFormat;

public class MulticastServer{
  public static void main(String[] args){
    long FIVE_SECONDS = 5000;
    boolean sfarsit=false; 
    int serverPort=7000, clientPort=7001;
    byte[] buf = new byte[256];
    Date data=null;
    try(DatagramSocket socket=new DatagramSocket(serverPort)){
      while (! sfarsit){
         data=new Date();    
         String df=DateFormat.getTimeInstance().format(data);
         buf = df.getBytes();
         InetAddress group = InetAddress.getByName("230.0.0.1");
         DatagramPacket packet = new DatagramPacket(buf, buf.length, group, clientPort);
         socket.send(packet);
         Thread.sleep(FIVE_SECONDS);
      }
    }  
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
