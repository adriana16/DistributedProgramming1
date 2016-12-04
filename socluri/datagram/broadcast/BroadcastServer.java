import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DateFormat;

public class BroadcastServer{
  public static void main(String[] args){
    long FIVE_SECONDS = 5000;
    boolean sfarsit=false;
    DatagramPacket packet = null;
    byte[] buf = new byte[256];
    Date data = null;
    int serverPort=7000, clientPort=7001;
    try(DatagramSocket socket=new DatagramSocket(serverPort)){
      while (! sfarsit) {
        data=new Date();    
        String df=DateFormat.getTimeInstance().format(data);
        buf = df.getBytes();
      // send it
        InetAddress group = InetAddress.getByName("255.255.255.255");
        packet = new DatagramPacket(buf, buf.length, group, clientPort);
        socket.setBroadcast(true);
        socket.send(packet);
      // sleep for a while
        Thread.sleep(FIVE_SECONDS);  
      } 
    }  
    catch (Exception e) {
       System.out.println(e.getMessage());
    }
  }
}
