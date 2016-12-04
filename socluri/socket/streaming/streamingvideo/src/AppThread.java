package streamingvideo;
import java.net.Socket;
// import java.io.InputStream;
import java.io.OutputStream;
// import javax.imageio.stream.FileImageOutputStream;
import java.io.DataInputStream;
//import javax.imageio.ImageIO;
import java.io.IOException;
//import java.io.File;
//import java.awt.image.BufferedImage;
//import java.awt.Image;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;


public class AppThread extends Thread{
  Socket socket=null;

  public AppThread(Socket socket){
    this.socket=socket;
  }

  public void run(){
    String path="../resources/videos/";
    try(OutputStream out=socket.getOutputStream();
      DataInputStream in=new DataInputStream(socket.getInputStream())){
      int noFile=in.readInt();
      System.out.println(noFile);
      
      String fileName="";
      if(noFile==1)
        fileName="airhorse.avi";
      else
        fileName="video1.avi";     
      Path cale=Paths.get(path+fileName);
      Files.copy(cale,out); 
      socket.close();
    }
    catch(Exception e){
       System.err.println("Server comunication error : "+e.getMessage());
    }  
  }
}
