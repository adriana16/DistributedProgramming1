import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Policy;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NameComponent;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.LifespanPolicyValue;

public class PersistentServer{
  public static void main( String args[] ) {
    Properties properties = System.getProperties();
    properties.put("org.omg.CORBA.ORBInitialHost","localhost");
    properties.put("org.omg.CORBA.ORBInitialPort","1050");
    try {
      // Pas 1: Crease si initializare ORB
      ORB orb = ORB.init(args, properties);

      // Pas 2: Crearea unui servant
      CmmdcImpl cmmdcImpl = new CmmdcImpl(orb);

      // Pas 3: Obtinerea unei referinte POA si 
      //        activarea gestionarului POAManager
      // *******************  
      // Pas 3-1: Obtinerea radacinii rootPOA 
      POA rootPOA=POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      // Pas 3-2: Create securitatii Persistent Policy
      Policy[] persistentPolicy = new Policy[1];
      persistentPolicy[0] = rootPOA.create_lifespan_policy(
        LifespanPolicyValue.PERSISTENT);
      // Pas 3-3: Crearea obiectului POA cu securitatea the Persistent Policy
      POA persistentPOA=rootPOA.create_POA("childPOA",null,persistentPolicy ); 
      // Pas 3-4: Activarea managerului PersistentPOA POAManager, 
      persistentPOA.the_POAManager().activate( );
      // ***********************
 
      // Pas 4: Asocierea servantului cu PersistentPOA
      persistentPOA.activate_object(cmmdcImpl);

      // Pas 5: Fixarea contextului RootNaming si legarea de  numele servantului
      // Numele serviciului de nume : 'NameService'
      // Numele servantului 'PersistentCmmdcServer'

      org.omg.CORBA.Object objRef=orb.resolve_initial_references("NameService");
      NamingContextExt rootContext=NamingContextExtHelper.narrow(objRef);
      NameComponent[] nc = rootContext.to_name("PersistentCmmdcServer");
      rootContext.rebind( nc,persistentPOA.servant_to_reference(cmmdcImpl));

      // Pas 6: Gata pentru satisfacerea clientilor
      orb.run();
    } 
    catch (Exception e) {
      System.err.println( "Exception in Persistent Server Startup "+e.getMessage());
    }
  }
}
