package cmmdc;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = {"/upgrade"}) 

public class UpgradeServlet extends HttpServlet{
  // Nu poate fi utilizat PrintWriter
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    if ("upgrade".equals(req.getHeader("Upgrade"))){
      res.setStatus(101);
      res.setHeader("X-Powered-By", "Servlet/3.1 "
        + "(GlassFish Server Open Source Edition 4.0 Java/Oracle Corporation/1.7)"); 
      res.setHeader("Upgrade", "upgrade");
      res.setHeader("Connection", "Upgrade");
      res.flushBuffer();
      System.out.println("Upgrade OK "+req.getHeader("Upgrade"));
      MyHttpUpgradeHandler handler = req.upgrade(MyHttpUpgradeHandler.class); 
    } 
    else{
      System.out.println("No upgrade: ");
    }
  }  
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}