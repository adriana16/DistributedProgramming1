import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.NameClassPair;
import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

class Lookup{
  public static void main(String[] args) {
    Context ctx=null;
    /*
    //  Varianta 1
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY, 
        "com.sun.jndi.fscontext.RefFSContextFactory");  
    try{
      ctx = new InitialContext(env);
    } 
    catch (NamingException e) {
      System.out.println("InitialContextError : "+e.getMessage());
    }
    */
    /*
    // Varianta 2
    System.setProperty("java.naming.factory.initial",
      "com.sun.jndi.fscontext.RefFSContextFactory");
    try{
      ctx = new InitialContext();
    } 
    catch (NamingException e) {
      System.out.println("InitialContextError : "+e.getMessage());
    }
    */
    
    // Varianta 3
    try{
      ctx = new InitialContext();
    } 
    catch (NamingException e) {
      System.out.println("InitialContextError : "+e.getMessage());
    }
      
     
    Scanner scanner=new Scanner(System.in);
    System.out.println("Introduceti referinta absoluta a unui catalog : ");
    String myName=scanner.next();
    try{
      System.out.println("\n ctx.lookup("+myName+") produce");
      System.out.println(ctx.lookup(myName));
      
      System.out.println("\nContinutul catalogului "+myName+" este:\n");
      NamingEnumeration lst=ctx.list(myName);
      while(lst.hasMore()){
        NameClassPair nc = (NameClassPair)lst.next();
        System.out.println(nc);
      }
      
      System.out.println("\nContinutul catalogului "+myName+" este:\n"); 
      NamingEnumeration lst1 = ctx.listBindings(myName);
      while (lst1.hasMore()) {
        Binding bd = (Binding)lst1.next();
        System.out.println(bd);
        //System.out.println(bd.getName() + ": " + bd.getObject());
      }        
      ctx.close();
    }
    catch (NamingException e) {
      System.out.println("Lookup failed: " + e.getMessage());
    }
  }
}
