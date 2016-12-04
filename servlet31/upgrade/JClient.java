import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class JClient{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    String sm=new Long(m).toString();
    System.out.println("n=");
    long n=scanner.nextLong();
    String sn=new Long(n).toString();
    String data=sm+" "+sn;
    String host="localhost";
    String port="8080";
    String contextRoot="/upgrade";
    
    String CRLF = "\r\n";
    String reqStr = "POST " + contextRoot + "/upgrade HTTP/1.1" + CRLF;
      reqStr += "User-Agent: Java/1.7" + CRLF;
      reqStr += "Host: " + host + ":" + port + CRLF;
      reqStr += "Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" + CRLF;
      reqStr += "Upgrade: upgrade" + CRLF;
      reqStr += "Connection: Upgrade" + CRLF;
      reqStr += "Content-type: application/x-www-form-urlencoded" + CRLF;
      reqStr += "Transfer-Encoding: chunked" + CRLF;
      reqStr += CRLF;
      reqStr += data + CRLF;
      
    Socket socket=null;
    try{
      socket=new Socket(host,Integer.parseInt(port));
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    try(
      OutputStream out=socket.getOutputStream();
      InputStream in=socket.getInputStream();     
      InputStreamReader isr=new InputStreamReader(in);
      BufferedReader br=new BufferedReader(isr);
    ){
      out.write(reqStr.getBytes());
      out.flush();
      String s;
      while((s=br.readLine())!=null){
        System.out.println(s);
      };  
      socket.close();  
      //System.exit(0);
    }
    catch(IOException e){
      System.out.println("Input Exception : "+e.getMessage());
    }
  }
}
