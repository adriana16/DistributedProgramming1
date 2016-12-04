package myservlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.AsyncContext;
import java.io.IOException;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.annotation.Resource;
import java.io.PrintWriter;

@WebServlet(urlPatterns="/cmmdc",asyncSupported=true)
public class AsyncServlet extends HttpServlet{
  
  @Resource private ManagedExecutorService executorService;
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    final AsyncContext asyncCtx=req.startAsync();
    executorService.submit(()->{
      res.setContentType("text/html");
      String sm=req.getParameter("m"),sn=req.getParameter("n");
      String tip=req.getParameter("tip");     
      long m=Long.parseLong(sm),n=Long.parseLong(sn);  
      long x=cmmdc(m,n);
      try{
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
        asyncCtx.complete();
      }
      catch(IOException e){}      
    });
  }  
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    doGet(req,res);
  }
  
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
}

