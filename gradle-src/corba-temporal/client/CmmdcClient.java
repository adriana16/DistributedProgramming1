import CmmdcApp.*;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CORBA.ORB;

public class CmmdcClient{
  static Cmmdc cmmdcImpl;

  public static void main(String args[]){
    long m=Long.parseLong(args[4]);
    long n=Long.parseLong(args[5]);
    try{
        // create and initialize the ORB
	    ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
	    orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolve the Object Reference in Naming
        String name = "CmmdcService";
        cmmdcImpl = CmmdcHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtained a handle on server object: " + cmmdcImpl);
        

		System.out.println("Cmmdc="+cmmdcImpl.cmmdc(m,n));


	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }
}