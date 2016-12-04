package cmmdc.web;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import cmmdc.ejb.Cmmdc;
import javax.ejb.EJB; 
import javax.servlet.annotation.WebServlet; 
import java.util.concurrent.Future;

@WebServlet(urlPatterns = "/cmmdc") 
public class CmmdcServlet extends HttpServlet{
  @EJB
  Cmmdc cb;
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    String sm=req.getParameter("m"),sn=req.getParameter("n");     
    long m=Long.parseLong(sm),n=Long.parseLong(sn);  
    Future<Long> result=cb.cmmdc(m,n);
    long x=0;
    try{
      while(! result.isDone()){;}; 
      x=result.get().longValue();
    }
    catch(Exception e){
      e.printStackTrace();
    }    
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