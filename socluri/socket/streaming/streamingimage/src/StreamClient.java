package streamingimage;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.net.Socket;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Scanner;

public class StreamClient{
  public static void main(String[] args){
    String host="localhost";
    int port=7998;
    System.out.println("Alegeti fisierul :");
    System.out.println("1 : xml-pic.jpg");
    System.out.println("2 : brasov.jpg");
    Scanner scanner=new Scanner(System.in);
    int noFile=scanner.nextInt();
    
    try(Socket clientSocket = new Socket(host, port); 
      InputStream in=clientSocket.getInputStream();
      DataOutputStream out=new DataOutputStream(clientSocket.getOutputStream())){
      out.writeInt(noFile);   
      BufferedImage bi=ImageIO.read(in);
      Image image=(Image)bi;
      ShowImage s=new ShowImage(image);
      s.show();
    } 
    catch(Exception e){
       System.err.println("Client comunication error : "+e.getMessage());
    }
  }
}


