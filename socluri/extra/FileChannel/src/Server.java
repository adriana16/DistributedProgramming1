import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server{
  public static void main(String[] args){
    int port = 7999;
    ServerSocketChannel ssc = null;
    try{
      ssc = ServerSocketChannel.open();
      InetSocketAddress isa = new InetSocketAddress(port);
      ServerSocket ss = ssc.socket();
      ss.bind(isa);		
    } 
    catch(IOException e){
      System.out.println("ServerSocketChannelError : "+e.getMessage());
      System.exit(0); 
    }
    System.out.println("Server ready...");
    while(true){
      serviciu(ssc);
    }
  }

  private static void serviciu(ServerSocketChannel ssc){
    String file = "xml-pic.jpg";
    String fs=System.getProperty("file.separator");
    String pathToFiles = "files";

    SocketChannel sc = null;
    try{
      sc = ssc.accept();
      File inputFile = new File(pathToFiles + fs + file);
      FileInputStream fis = new FileInputStream(inputFile);
      FileChannel fileChannel = fis.getChannel();

      // Trimit dimensiunea fisierului catre client
      long fileSize = fileChannel.size();
      ByteBuffer bb = ByteBuffer.allocate(Long.SIZE / 8);
      LongBuffer buf = bb.asLongBuffer();
      buf.put(fileSize);
      sc.write(bb);

      // Transfer fisierul catre client
      fileChannel.transferTo(0, fileSize, sc);

      System.out.println("Fisier expediat : " + file);
    } 
    catch (Exception e){
      System.out.println("ServiceError : " + e.getMessage());
    }
  }
}
