package app;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class AppShiro{

  private static final transient Logger log = 
    LoggerFactory.getLogger(AppShiro.class);

  public static void main(String[] args) {

    // The easiest way to create a Shiro SecurityManager with configured
    // realms, users, roles and permissions is to use the simple INI config.
    // We'll do that by using a factory that can ingest a .ini file and
    // return a SecurityManager instance:

    // Use the shiro.ini file at the root of the classpath
    // (file: and url: prefixes load from files and urls respectively):
    Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    SecurityManager securityManager = factory.getInstance();

    // for this simple example quickstart, make the SecurityManager
    // accessible as a JVM singleton.  Most applications wouldn't do this
    // and instead rely on their container configuration or web.xml for
    // webapps.  That is outside the scope of this simple quickstart, so
    // we'll just do the bare minimum so you can continue to get a feel
    // for things.
    SecurityUtils.setSecurityManager(securityManager);

    // Now that a simple Shiro environment is set up, let's see what you can do:

    // get the currently executing user:
    Subject currentUser = SecurityUtils.getSubject();

    // let's login the current user so we can check against roles and permissions:
    Scanner scanner=new Scanner(System.in);
    if (!currentUser.isAuthenticated()) {
      System.out.println("Username:");
      String username=scanner.next();
      System.out.println("Password:");
      String passwd=scanner.next();
        UsernamePasswordToken token = new UsernamePasswordToken(username, passwd);
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
        }
    }

    //say who they are:
    //print their identifying principal (in this case, a username):
    //log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

    //test a role:
    if (currentUser.hasRole("admin")) {
        //log.info("You are the admin !");
        MyService obj=new MyService();
        obj.service();
    } 
    /*
    else {
        log.info("You are NOT the admin.");
    }
    */
    else{
    //test a typed permission (not instance-level)
      if (currentUser.isPermitted("cmmdc")) {
          //log.info("Use it wisely.");
          System.out.println("m=");
          long m=scanner.nextLong();
          System.out.println("n=");
          long n=scanner.nextLong();
          Cmmdc o1=new Cmmdc();
          long r1=o1.cmmdcService.cmmdc(m,n);
          System.out.println("Cmmdc: "+r1);
      }
    }
    /*    else {
          log.info("Sorry");
      }
    */
    //all done - log out!
    currentUser.logout();
    System.exit(0);
  }
}
