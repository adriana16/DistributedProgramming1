import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import java.io.File;

public class EmbeddedTomcat{
  public static void main(String[] args) {
    try {
      Tomcat tomcat = new Tomcat();
      tomcat.setBaseDir(".");
      tomcat.setPort(9090);

      File docBase = new File(".");
      Context ctxt = tomcat.addContext("/", docBase.getAbsolutePath());

      Tomcat.addServlet(ctxt, "hello", new HelloServlet());
      ctxt.addServletMapping("/hello", "hello");

      Tomcat.addServlet(ctxt, "cmmdc", new CmmdcServlet());
      ctxt.addServletMapping("/cmmdc", "cmmdc");
      
      tomcat.start();
      tomcat.getServer().await();
    } 
    catch (Exception e) {
      e.printStackTrace();  
    }
  }  
}