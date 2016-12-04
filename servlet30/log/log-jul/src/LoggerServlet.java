package logtest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.util.logging.Logger; 
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/logging") 

public class LoggerServlet extends HttpServlet {

  private static Logger logger=
    Logger.getLogger(LoggerServlet.class.getCanonicalName());
  
  public void init(){
    try{
      String filePath=System.getProperty("catalina.home")+"/webapps/loggerJUL/results.log";
      FileHandler loggingFile = new FileHandler(filePath);
      loggingFile.setFormatter(new SimpleFormatter());
      logger.addHandler(loggingFile);
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, java.io.IOException {

    logger.info("INFO : Hello");
    logger.warning("WARN : Hello");
    logger.severe("ERROR : Hello");
    
    res.setContentType("text/html");
    java.io.PrintWriter out = res.getWriter();
    out.println("<html><head><title>Servlet logging</title></head><body>");
    out.println("<h2>Hello from LoggerServlet</h2>");
    out.println("<br/>");
    out.println("<a href=\"http://"+
       req.getServerName()+":"+
       req.getLocalPort()+
       req.getContextPath()+"/"+
       "results.log\">Vizualizati fisierul log</a>");
    out.println("</body></html>");
    out.close();
  } 

  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, java.io.IOException {
    doGet(req, res);
  }
}

           
         
  