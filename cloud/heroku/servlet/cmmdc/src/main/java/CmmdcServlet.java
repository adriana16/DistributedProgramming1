package cmmdc.heroku;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.io.PrintWriter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class CmmdcServlet extends HttpServlet{
  public long cmmdc(long m, long n){
     long r,c;
     do{
        c=n;
        r=m%n;
        m=n;
        n=r;
     }while(r!=0);
     return c;
  }   
   
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    String sm=req.getParameter("m"),sn=req.getParameter("n");
    String tip=req.getParameter("tip");     
    long m=Long.parseLong(sm),n=Long.parseLong(sn);  
    long x=cmmdc(m,n);
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
  
  public static void main(String[] args) throws Exception{
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    ServletContextHandler context = 
      new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/*");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new CmmdcServlet()),"/*");
    server.start();
    server.join();
  }
}