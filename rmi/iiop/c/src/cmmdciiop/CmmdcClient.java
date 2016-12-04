package cmmdciiop;
import javax.rmi.PortableRemoteObject;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Scanner;
import cmmdc.ICmmdc;

public class CmmdcClient {
  public static void  main( String args[] ) {
    String host="localhost";
    String port="1050";
    if (args.length>0)
       host=args[0];
    if (args.length>1)
       port=args[1];
    
    Scanner scanner=new Scanner(System.in);
    System.out.println("Primul numar :");
    long m=Long.parseLong(scanner.next());
    System.out.println("Al doilea numar :");
    long n=Long.parseLong(scanner.next());    
    try {
      System.setProperty("java.naming.factory.initial","com.sun.jndi.cosnaming.CNCtxFactory");
      System.setProperty("java.naming.provider.url","iiop://"+host+":"+port);
      Context ctx = new InitialContext();
     
      // STEP 1: Get the Object reference from the Name Servctxe
      // using JNDI call.
      Object objref = ctx.lookup("CmmdcService");
      System.out.println("Client: Obtained a ref. to Cmmdc server.");

      // STEP 2: Narrow the object reference to the concrete type and
      // invoke the method.
      ICmmdc obj = (ICmmdc) PortableRemoteObject.narrow(objref,ICmmdc.class);
      long x=obj.cmmdc(m,n);
      System.out.println("Cmmdc="+x); 
    } 
		catch( Exception e ) {
      System.out.println( "Exception " + e.getMessage());
    }
  }
}
