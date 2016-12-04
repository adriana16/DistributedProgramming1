import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/logging") 

public class LoggerServlet extends HttpServlet {
  private static Logger logger=LoggerFactory.getLogger(LoggerServlet.class);

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, java.io.IOException {
    logger.trace("TRACE : Hello"); 
    logger.debug("DEBUG : Hello");
    logger.info("INFO : Hello");
    logger.warn("WARN : Hello");
    logger.error("ERROR : Hello");
    
    res.setContentType("text/html");
    java.io.PrintWriter out = res.getWriter();
    out.println("<html><head><title>Servlet logging</title></head><body>");
    out.println("<h2>Hello from LoggerServlet</h2>");
    out.println("<br/>");
    out.println("<a href=\"http://"+
       req.getServerName()+":"+
       req.getLocalPort()+
       req.getContextPath()+ "/"+
       "results.log\">Vizualizati fisierul log</a>");
    out.println("</body></html>");
    out.close();
  } 

  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, java.io.IOException {
    doGet(req, res);
  }
}

           
         
  