import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Scanner;

public class LDAPClientCmmdc {
  public static void main(String[] args) {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY,
      "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL,
        "ldap://localhost:10389/uid=admin,ou=system");
    env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system"); 
    env.put(Context.SECURITY_CREDENTIALS, "secret"); 
    DirContext ctx = null;
    try {
      ctx=new InitialDirContext(env);
      if (ctx != null) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduceti valoarea atributului \"cn\" "+
        " a obiectului Cmmdc");
        System.out.println("cn=");
        String cmmdcObj=scanner.next().trim();
        Object object = ctx.lookup("cn="+cmmdcObj);  			
        System.out.println("Primul numar");
        long a=scanner.nextInt();
        System.out.println("Al doilea numar");
        long b=scanner.nextInt();
        Cmmdc obj=(Cmmdc) object;
        System.out.println("Rezultatul cmmdc este: "+obj.cmmdc(a,b));
        ctx.close();
      }
    } 
    catch (NamingException e) {
      System.out.println("LDAPClientCmmdc :  "+e.getMessage());
    }
  }
}
