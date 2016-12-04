package cmmdc.web;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cmmdc.ejb.Cmmdc;
import javax.naming.Context;
import javax.naming.InitialContext; 
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/cmmdc") 
public class CmmdcServlet extends HttpServlet{
  Cmmdc cb;
  
  public void init(ServletConfig config) throws ServletException{
    super.init(config);
    Context ctx=null;
    try{
      ctx=new InitialContext();
      cb=(Cmmdc)ctx.lookup("java:global/cmmdc-ear/cmmdc-ejb/CmmdcBean");     
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
    }    
  }
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    String sm=req.getParameter("m"),sn=req.getParameter("n");     
    long m=Long.parseLong(sm),n=Long.parseLong(sn);  
    long x=cb.cmmdc(m,n);
    PrintWriter out=res.getWriter();
   
    String title="Cmmdc Servlet";
    res.setContentType("text/html");
    out.println("<HTML><HEAD><TITLE>");
    out.println(title);
    out.println("</TITLE></HEAD><BODY>");
    out.println("<H1>"+title+"</H1>");
    out.println("<P>Cmmdc : "+x);
    out.println("</BODY></HTML>");
     
    out.close();   
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}