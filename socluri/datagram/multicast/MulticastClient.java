import java.io.*;
import java.net.*;

public class MulticastClient {
  public static void main(String[] args) throws IOException {
    DatagramPacket packet;
    byte[] buf = new byte[256];
    int clientPort=7001;
    MulticastSocket socket = new MulticastSocket(clientPort);
    InetAddress address = InetAddress.getByName("230.0.0.1");
    socket.joinGroup(address);
    int i=-1;
    do{
       i++;
       packet = new DatagramPacket(buf, buf.length);
       socket.receive(packet);
       String received = new String(packet.getData());
       System.out.println("Am primit:  " + received);
    }
    while(i<5);
    socket.leaveGroup(address);
    socket.close();
  }
}
