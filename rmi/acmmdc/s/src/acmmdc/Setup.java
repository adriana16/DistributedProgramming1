package acmmdc; 
import java.rmi.*;
import java.rmi.activation.*;
import java.rmi.registry.*;
import java.util.Properties;

public class Setup{
  private Setup() {}

  public static void main(String[] args) throws Exception {
    // Argumentul liniei de comanda
    String implClass = "";
    if (args.length < 1) {
       System.out.println("usage: ");
       System.out.println("java [options] acmmdc.Setup <implClass>");
       System.exit(1);
    }
    else {
      implClass = args[0];
    }

    // Construirea descriptorului grupului de activare
    String policy=System.getProperty("myactivation.policy","group.policy");
    String implCodebase=System.getProperty("myactivation.impl.codebase");
    String filename=System.getProperty("myactivation.file", "");
    Properties props = new Properties();
    props.put("java.security.policy", policy);
    props.put("java.class.path", "no_classpath");
    props.put("myactivation.impl.codebase", implCodebase);
    if (filename != null && !filename.equals("")) {
      props.put("myactivation.file", filename);
    }
    ActivationGroupDesc groupDesc = new ActivationGroupDesc(props, null);

    // Inregistrarea grupului de activare pentr obtinerea 
    // identificatorului de activare 
    ActivationGroupID groupID=
      ActivationGroup.getSystem().registerGroup(groupDesc);
    System.err.println("Activation group descriptor registered.");

    // Construirea descriptorului de activare
    MarshalledObject data = null;
    if (filename != null && !filename.equals("")) {
      data = new MarshalledObject(filename);
    }

    ActivationDesc desc=
      new ActivationDesc(groupID,implClass,implCodebase,data);

    // Inregistrarea descriptorului de activare
    Remote stub = Activatable.register(desc);
    System.err.println("Activation descriptor registered.");
    
    // Inregistrarea serviciului in registry
    String name = System.getProperty("myactivation.name");
    LocateRegistry.getRegistry().rebind(name, stub);
    System.err.println("Stub bound in registry.");
  }
}