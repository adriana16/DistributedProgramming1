import SumApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class SumClient{
  static Sum SumImpl;

  public static void main(String args[]){
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
        String name = "SumService";
        SumImpl = SumHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtained a handle on server object: " + SumImpl);
        double[] s={0.1,0.2,0.3,0.4};

		System.out.println("Sum="+SumImpl.compute(s));


	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }
}