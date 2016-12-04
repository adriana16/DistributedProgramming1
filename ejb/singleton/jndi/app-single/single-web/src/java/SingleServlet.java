package single.web;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import single.ejb.Single;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/single") 
public class SingleServlet extends HttpServlet{
  Single sb;
  
  public void init(ServletConfig config) throws ServletException{
    super.init(config);
    Context ctx=null;
    try{
      ctx=new InitialContext();
      sb=(Single)ctx.lookup("java:global/single-ear/single-ejb/SingleBean");     
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
    }    
  }

  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    int x=sb.getIndex();
    PrintWriter out=res.getWriter();
    res.setContentType("text/html");
    
    String title="Single Servlet";
    out.println("<HTML><HEAD><TITLE>");
    out.println(title);
    out.println("</TITLE></HEAD><BODY>");
    out.println("<H1>"+title+"</H1>");
    out.println("<p>Valoarea index : "+x);
    out.println("</BODY></HTML>");
     
    out.close();   
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}