package client;
import cmmdc.ICmmdc;
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
    if(args.length>2)
      host=args[2];
    if (args.length>3)
      port=Integer.parseInt(args[3]);
    //System.out.println("Primul numar :");
    long m=Long.parseLong(args[0]);
    //System.out.println("Al doilea numar :");
    long n=Long.parseLong(args[1]);
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
