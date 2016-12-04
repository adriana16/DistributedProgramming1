import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet; 
import java.io.PrintWriter;
import com.google.inject.Singleton;
import com.google.inject.Guice;
import com.google.inject.Injector;
import service.ICmmdc;

@Singleton
@WebServlet(urlPatterns = "/cmmdc") 

public class CmmdcGuiceServlet extends HttpServlet{
  private ICmmdc obj;
  
  public void init(ServletConfig config) throws ServletException{
    super.init(config);
    Injector injector = Guice.createInjector(new AppModule() );    
    obj = injector.getInstance(ICmmdc.class);
  }
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    String sm=req.getParameter("m"),sn=req.getParameter("n");
    String tip=req.getParameter("tip");     
    long m=Long.parseLong(sm),n=Long.parseLong(sn);  
    //Injector injector = Guice.createInjector(new AppModule() );    
    //ICmmdc obj = injector.getInstance(ICmmdc.class);
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