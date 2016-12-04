import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*; 

public class HelloServlet extends HttpServlet {
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    ServletOutputStream out=res.getOutputStream(); 
    String nume=req.getParameter("name");
    String tip=req.getParameter("tip");
    req.getSession(true); 
    if(tip.equals("text/html")){
      res.setContentType("text/html");
      out.println("<html>");
      out.println("<head><title>HelloServlet</title></head>");
      out.println("<body>");
      out.println("<h1>HelloServlet</h1>");
      out.println("<p>");
      out.println( "Hi "+ nume+" !");
      out.println("<br>");
      out.println("Users : "+FirstSessionListener.getConcurrentUsers());
      out.println("</p>");
      out.println("</body>");
      out.println("</html>");
    }
    else{
      res.setContentType("text/plain");
      out.println("Hi "+nume+" !");
    }
    out.close();
  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    doGet(req,res);
  }
}
