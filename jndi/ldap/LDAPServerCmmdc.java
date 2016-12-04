import java.util.Hashtable;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPServerCmmdc {
  public static void main(String[] args) {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY,
    		"com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL,
        "ldap://localhost:10389/uid=admin,ou=system");
    env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system"); 
    env.put(Context.SECURITY_CREDENTIALS, "secret"); 
    DirContext ctx = null;	
    Scanner scanner=new Scanner(System.in);
    System.out.println("Alegeti operatia : 1- bind; 2- unbind");
    int oper=scanner.nextInt();
    System.out.println("Introduceti valoarea atributului \"cn\" "+
        " a obiectului Cmmdc");
    System.out.println("cn=");
    String cmmdcObj=scanner.next().trim();	
    try {
      ctx = new InitialDirContext(env);
      if(oper==1){
    	  Cmmdc obj=new Cmmdc();
    	  String str="cn="+cmmdcObj;
    	  ctx.bind(str,obj);
      }
      else{
        ctx.unbind("cn="+cmmdcObj);
      }
      ctx.close();
    }
    catch (NamingException e) {
      System.out.println("LDAPserverCmmdc :  "+e.getMessage());
    }
  }
}
