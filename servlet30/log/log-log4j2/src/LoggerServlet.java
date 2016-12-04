import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/logging") 

public class LoggerServlet extends HttpServlet {
  static Logger logger = null;

  public void init() {
    logger = LogManager.getLogger(LoggerServlet.class);
    logger.info("LoggerServlet started.");
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, java.io.IOException {  
    logger.warn("WARN : Hello");
    logger.debug("DEBUG : Hello");
    logger.info("INFO : Hello");
    //logger.fatal("FATAL : Hello");
    
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

  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException, java.io.IOException {
    doGet(req, res);
  }
}

           
         
  