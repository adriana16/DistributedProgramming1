import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import java.io.PrintWriter;
import com.google.inject.Singleton;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Inject;
import service.ICmmdc;

@Singleton
public class CmmdcGuiceServlet extends HttpServlet{
  @Inject
  private ICmmdc obj;
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    String sm=req.getParameter("m"),sn=req.getParameter("n");
    String tip=req.getParameter("tip");     
    long m=Long.parseLong(sm),n=Long.parseLong(sn);  
    long x=obj.cmmdc(m,n);
    PrintWriter out=res.getWriter();
    if(tip.equals("text/html")){
      String title="Cmmdc Servlet";
      res.setContentType("text/html");
      out.println("<HTML><HEAD><TITLE>");
      out.println(title);
      out.println("</TITLE></HEAD><BODY>");
      out.println("<H1>"+title+"</H1>");
      out.println("<P>Cmmdc is "+x);
      out.println("</BODY></HTML>");
    }
    else{
      res.setContentType("text/plain");
      out.println(x);
    }
    out.close();   
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}