package client;
import cmmdc.ICmmdc;
import java.util.Scanner;
// Varianta cu apel rmiregistry direct
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
// Varianta JNDI
import javax.naming.Context;
import javax.naming.InitialContext;

public class CmmdcClient {
  public static void main(String args[]) {
    String host="localhost";
    int port=1099;
    if(args.length>0)
      host=args[0];
    if (args.length>1)
      port=Integer.parseInt(args[1]);
    Scanner scanner=new Scanner(System.in);
    System.out.println("Primul numar :");
    long m=Long.parseLong(scanner.next());
    System.out.println("Al doilea numar :");
    long n=Long.parseLong(scanner.next());
    long x=0;
    try {
      // Varianta cu apel rmiregistry direct
      /*
      Registry registry=LocateRegistry.getRegistry(host,port);
      ICmmdc obj=(ICmmdc)registry.lookup("CmmdcServer");
      */
      // Varianta JNDI
      String sPort=(new Integer(port)).toString();
      System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.rmi.registry.RegistryContextFactory");
      System.setProperty(Context.PROVIDER_URL,"rmi://"+host+":"+sPort);
      Context ctx=new InitialContext();
      ICmmdc obj=(ICmmdc)ctx.lookup("CmmdcServer");
      
      x=obj.cmmdc(m,n);
      System.out.println("Cmmdc="+x);
    }
    catch (Exception e) {
      System.out.println("CmmdcClient exception: "+e.getMessage());
    }
  }
}
