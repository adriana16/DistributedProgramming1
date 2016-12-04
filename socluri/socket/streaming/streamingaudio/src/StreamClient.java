package streamingaudio;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.EOFException;
import java.io.UTFDataFormatException;
import java.util.Scanner;

public class StreamClient{
  public static void main(String[] args){
    String host="localhost";
    int port=7999;
    System.out.println("Alegeti fisierul :");
    System.out.println("1 : TomJones.mp3");
    Scanner scanner=new Scanner(System.in);
    int noFile=scanner.nextInt();
    try(Socket clientSocket = new Socket(host, port);    
      InputStream in = clientSocket.getInputStream();
      DataOutputStream out=new DataOutputStream(clientSocket.getOutputStream())){    
      out.writeInt(noFile);
      FileOutputStream fos=new FileOutputStream("audiofile.mp3");
      
      int i;
      byte[] b=new byte[8192];
      while((i=in.read(b,0,b.length))!=-1) {
        fos.write(b,0,b.length);
        fos.flush();
      }  
      fos.close();
      FileInputStream fis=new FileInputStream("audiofile.mp3");
      // De ce nu merge direct de pe inputstream?
      
      System.out.println("Play MP3");   
      MP3Player mp3Player=new MP3Player(fis);    
      mp3Player.start();       
    } 
   
    catch(IOException e){
       System.err.println("Client comunication error : "+e.getMessage());
    }
  }
}


