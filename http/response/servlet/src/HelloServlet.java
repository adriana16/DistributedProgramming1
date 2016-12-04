import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*; 
import java.util.Enumeration;

public class HelloServlet extends HttpServlet {
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    ServletOutputStream out=res.getOutputStream(); 
    String nume=req.getParameter("name");
    String tip=req.getParameter("tip");
    if(tip.equals("text/html")){
      String method=req.getMethod();
      String path=req.getServletPath();
      Enumeration<String> headerNames=req.getHeaderNames();
    
      res.setContentType("text/html");
      out.println("<html>");
      out.println("<head><title>HelloServlet</title></head>");
      out.println("<body>");
      out.println("Method : "+method);
      out.println("<br/>");
      out.println("ServletPath : "+path);
      out.println("<br/>");
      while(headerNames.hasMoreElements()){
        String headerName=headerNames.nextElement();
        out.println("Header name : "+headerName);
        out.println("<br/>");
        Enumeration<String> headerValues=req.getHeaders(headerName);
        while(headerValues.hasMoreElements()){
          String headerValue=headerValues.nextElement();
          out.println("    Header value : "+headerValue);
          out.println("<br/>");
        }
      }
      out.println("<h1>HelloServlet</h1>");
      out.println("<p>");
      out.println( "Hi "+ nume+" !");
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
