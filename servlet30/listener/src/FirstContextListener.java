import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

public class FirstContextListener
    implements ServletContextListener {
  public void contextDestroyed(ServletContextEvent event) {
    System.out.println("Web app was removed.");
  }
  public void contextInitialized(ServletContextEvent event) {
    System.out.println("Web app is ready.");
    ServletContext sc=event.getServletContext();
    System.out.println(sc.getContextPath());
    System.out.println(sc.getEffectiveMajorVersion());
    System.out.println(sc.getEffectiveMinorVersion());
  }
}
