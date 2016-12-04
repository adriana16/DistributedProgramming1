package streamingvideo;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class StreamClientJMF{
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
      /*      
      FileOutputStream fos=new FileOutputStream(file);
      int i;
      byte[] b=new byte[8192];
      while((i=in.read(b,0,b.length))!=-1) {
        fos.write(b,0,b.length);
        fos.flush();
      }  
      fos.close();
      */
      
      // start video player
      String absolutePath=file.getAbsolutePath();
      System.out.println(absolutePath);
      URL mediaURL=null;          
      try {
        mediaURL = new URL("file://"+absolutePath);				
        System.out.println(mediaURL);
      } 
      catch(MalformedURLException e) {
        System.out.println(e);
      }
     
      JFrame mediaTest = new JFrame("Movie Player");
      mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      MediaPlayer mediaPanel = new MediaPlayer(mediaURL);
      mediaTest.add(mediaPanel);
      mediaTest.setSize(800, 700); // set the size of the player
      mediaTest.setLocationRelativeTo(null);
      mediaTest.setVisible(true);	
    } 
    catch(Exception e){
       System.err.println("Client comunication error : "+e.getMessage());
       e.printStackTrace();
    }
  }
}


