package streamingtext;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.EOFException;
import java.io.UTFDataFormatException;
import java.util.Scanner;

public class StreamClient{
  public static void main(String[] args){
    String host="localhost";
    int port=7997;
    System.out.println("Alegeti fisierul :");
    System.out.println("1 : capitol.txt");
    System.out.println("2 : junit.tex");
    Scanner scanner=new Scanner(System.in);
    int noFile=scanner.nextInt();
    try(Socket clientSocket = new Socket(host, port);    
      DataInputStream in = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream out=new DataOutputStream(clientSocket.getOutputStream())){    
      out.writeInt(noFile);
      System.out.println("Received : ");
      String s;
      while(!(s=in.readUTF()).equals("endOFfile"))System.out.println(s);
    } 
   
    catch(IOException e){
       System.err.println("Client comunication error : "+e.getMessage());
    }
  }
}


