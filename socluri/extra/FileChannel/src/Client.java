import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Client{
  private static String path = "repository";
  private static String file = "xml-pic.jpg"; 

  public static void main(String[] args){
    String host="localhost";
    int port=7999;
    if (args.length>0)
      host=args[0];
    if (args.length>1)
      port=Integer.parseInt(args[1]);
    SocketChannel sc = null;
    String fs=System.getProperty("file.separator");
    try{
      InetSocketAddress isa = new InetSocketAddress(host, port);
      sc = SocketChannel.open();
      sc.connect(isa);

      // Se preia dimensiunea fisierului(pentru verificare)
      ByteBuffer bb = ByteBuffer.allocate(Long.SIZE / 8);
      sc.read(bb);
      long fileSize = bb.getLong(0);

      // Deschid canalul
      String fileOutName = path + fs + file;
      File filePath = new File(path);
      if (!filePath.exists())			{
        filePath.mkdirs();
      }
      FileOutputStream fos = new FileOutputStream(new File(fileOutName));
      FileChannel fc = fos.getChannel();

      fc.transferFrom(sc, 0, fileSize);
      System.out.println("Fisier receptionat "+file+ " de "+fileSize + " octeti");
      sc.close();
      fos.close();
      fc.close();
    } 
    catch(IOException e){
      System.out.println("ClientError : "+e.getMessage());
    }		
  }
}
