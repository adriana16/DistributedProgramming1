package streamingaudio;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.OutputStream;
//import java.io.InputStream;
//import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class AppThread extends Thread{
  private Socket socket=null;

  public AppThread(Socket socket){
    this.socket=socket;
  }

  public void run(){
    String path="../resources/audio/";
    try(DataInputStream in = new DataInputStream(socket.getInputStream());
      OutputStream out=socket.getOutputStream()){
      int noFile=in.readInt();
      System.out.println(noFile);
      
      String fileName="";
      if(noFile==1)
        fileName="TomJones.mp3"; 
      Path cale=Paths.get(path+fileName);
      Files.copy(cale,out);        
      /*  
      InputStream fis=new FileInputStream(path+fileName);        
      
      int s; 
      byte[] b=new byte[8192];
      while((s=fis.read(b,0,b.length))!=-1){
        out.write(b,0,b.length);   
        out.flush();
      }  
      out.flush();  
      */      
      System.out.println("Activity Finished !");
      socket.close();
    }
    catch(IOException e){
       System.err.println("Server comunication error : "+e.getMessage());
    }  
  }
}
