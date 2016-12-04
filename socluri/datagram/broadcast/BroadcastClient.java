import java.io.*;
import java.net.*;
import java.util.*;

public class BroadcastClient {

public static void main(String[] args) throws IOException{
int clientPort=7001;
DatagramSocket socket=new DatagramSocket(clientPort);
DatagramPacket packet;
byte[] buf = new byte[256];
int i=-1;
do{
   i++;      
   packet = new DatagramPacket(buf, buf.length);
   socket.receive(packet);
   String received = new String(packet.getData());
   System.out.println("Am primit:  " + received);
}
while(i<5);
socket.close();
}

}
