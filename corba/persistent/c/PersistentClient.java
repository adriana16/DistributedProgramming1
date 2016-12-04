import CmmdcApp.*;
import org.omg.CORBA.ORB;
import java.util.Scanner;

public class PersistentClient {
  public static void main(String args[]) {
		String host="localhost";
		String port="1050";
		if(args.length>0)
			host=args[0];
		if(args.length>1)
			port=args[1];
    try {
      // Pas 1: Initializare ORB
      ORB orb = ORB.init(args, null);

      // Pas 2: Rezolvarea persistentei
			// Serviciul NameService ruleaza pe host cu portul port
			// Numele serviciului cerut lui NameService este 
			// "PersistentCmmdcServer"
      org.omg.CORBA.Object obj = orb.string_to_object( 
        "corbaname::"+host+":"+port+"#PersistentCmmdcServer");
      Cmmdc app=CmmdcHelper.narrow(obj);

      // Pas 3: Utilizarea serviciului
			Scanner scanner=new Scanner(System.in);
      System.out.println( "Calling Persistent Server.." );
      long m,n;
      System.out.println("m=");
      m=scanner.nextLong();
      System.out.println("n=");
      n=scanner.nextLong();
      System.out.println(app.cmmdc(m,n));
    }
		catch ( Exception e ) {
      System.err.println( "Exception in PersistentClient.java..." + e );
      e.printStackTrace( );
    }
  }
}
