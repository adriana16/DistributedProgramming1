import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CmmdcClient {
  public static void main(String[] args) throws IOException {

    String host="localhost";
    int port=7999;
    if (args.length>0)
       host=args[0];
    if (args.length>1)
       port=Integer.parseInt(args[1]);
    Scanner scanner=new Scanner(System.in);
    long m,n,r;
    System.out.println("m=");
    m=scanner.nextLong();
    System.out.println("n=");
    n=scanner.nextLong();
    try(Socket cmmdcSocket = new Socket(host, port); 
      DataInputStream in=new DataInputStream(cmmdcSocket.getInputStream());
      DataOutputStream out=new DataOutputStream(cmmdcSocket.getOutputStream())){
        out.writeLong(m);
        out.writeLong(n);
        r=in.readLong();
        System.out.println("Required result : "+r);
    } 
    catch(Exception e){
       System.err.println("Client comunication error : "+e.getMessage());
    }
  }
}


