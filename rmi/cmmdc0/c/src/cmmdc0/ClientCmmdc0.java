package cmmdc0;
import java.util.Scanner;

public class ClientCmmdc0{
  public static void main(String args[]){
    String host="localhost";
    int port=1099;
    if(args.length>0)
      host=args[0];
    if(args.length>1)
      port=Integer.parseInt(args[1]);
    Scanner scanner=new Scanner(System.in);
    try{
      RemoteClient ct=new RemoteClient(host,port);
      ct.remote.setMethod(ct);
      System.out.println("m=");
      long m=scanner.nextLong();
      System.out.println("n=");
      long n=scanner.nextLong();
      long x=ct.remote.compute(m,n);
      System.out.println("Cmmdc="+x);
    }
    catch(Exception e){
      System.out.println("ClientException: "+e.getMessage());
    }
    System.exit(0);
  }
}