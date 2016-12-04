package myservlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import listeners.MyAsyncListener;

@WebServlet(urlPatterns="/cmmdc",asyncSupported=true)
public class AsyncServlet extends HttpServlet{
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    final AsyncContext asyncCtx=req.startAsync(req,res);
    asyncCtx.addListener(new MyAsyncListener());
    asyncCtx.start(new Runnable() {
      public void run() {
        ServletRequest req=asyncCtx.getRequest();
        ServletResponse res=asyncCtx.getResponse();
        String sm=req.getParameter("m");
        String sn=req.getParameter("n");     
        long m=Long.parseLong(sm),n=Long.parseLong(sn);  
        long x=cmmdc(m,n);
        String result=new Long(x).toString();
        System.out.println(x);
        try{
          PrintWriter out=res.getWriter();
          String title="Cmmdc Servlet";
          res.setContentType("text/html");
          out.println("<HTML><HEAD><TITLE>");
          out.println(title);
          out.println("</TITLE></HEAD><BODY>");
          out.println("<H1>"+title+"</H1>");
          out.println("<P>Cmmdc is "+x);
          out.println("</BODY></HTML>");
          out.close();
        }
        catch(IOException e){
          System.out.println(e.getMessage());
        } 
        asyncCtx.complete();
      }
    });  
  }  
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    doGet(req,res);
  }
  
  private long cmmdc(long m, long n){
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

