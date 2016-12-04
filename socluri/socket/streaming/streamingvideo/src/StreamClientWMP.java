package streamingvideo;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class StreamClientWMP{
  public static void main(String[] args){
    String host="localhost";
    int port=7998;
    System.out.println("Alegeti fisierul :");
    System.out.println("1 : airhorse.avi");
    System.out.println("2 : video1.avi");
    Scanner scanner=new Scanner(System.in);
    int noFile=scanner.nextInt();
    String fileName="";
    if(noFile==1) 
      fileName="airhorse.avi";
    else
      fileName="video1.avi";
    
    try(Socket clientSocket = new Socket(host, port); 
      InputStream in=clientSocket.getInputStream();
      DataOutputStream out=new DataOutputStream(clientSocket.getOutputStream())){
      out.writeInt(noFile);
      File file=new File(fileName);   
      
      Path cale=Paths.get(fileName);
      Files.copy(in,cale); 
    
      // start video player
      String absolutePath=file.getAbsolutePath();
      String windowsMediaPlayerPath = 
        "\"C://Program Files (x86)/Windows Media Player/wmplayer.exe\" ";
      // Fixarea parametrilor WMP
      String wmpParam = windowsMediaPlayerPath + absolutePath;
      
      Process runningProcess = null;			    
      try{	
        runningProcess = Runtime.getRuntime().exec(wmpParam);							
      }
      catch(Exception ex){
        System.out.println(ex);
      }						
      try {
        while(true){					
          // Se asteapta terminarea procesului WMP
          int i = runningProcess.waitFor();
          if (i==0){
            runningProcess.destroy();
            break;
          }
        }
      }
      catch(InterruptedException e) {
        System.out.println(e);
      }	
      Runtime.getRuntime().exit(0);
    } 
    catch(Exception e){
       System.err.println("Client comunication error : "+e.getMessage());
       e.printStackTrace();
    }
  }
}


