import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class RequestHTTPMsg{
  public static void main(String[] args){
    Socket socket=null;
    try{
      ServerSocket serverSocket=new ServerSocket(9090);
      socket=serverSocket.accept();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    try(
      InputStream is=socket.getInputStream();
      InputStreamReader isr=new InputStreamReader(is);
      BufferedReader br=new BufferedReader(isr);
      FileWriter fw=new FileWriter("outputH.txt",true);
      BufferedWriter bw=new BufferedWriter(fw)
    ){
      for(;;){
        String s=br.readLine();
        if(s==null)break;
        System.out.println(s);
        bw.write(s);
        bw.newLine();
      }
    }
    catch(IOException e){
      System.out.println("Input Exception : "+e.getMessage());
    }
  }
}
