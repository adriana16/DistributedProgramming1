package myservlet;
import javax.servlet.AsyncContext;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

class AsyncAction{
  
  interface CmmdcService {
      long cmmdc(long m, long n);
  }

  static CmmdcService cmmdcService=(long m, long n) -> 
    { 
      long r,c;
      do{
         c=n;
         r=m%n;
         m=n;
         n=r;
      }
      while(r!=0);
      return c;
    }; 
    
  interface IAsyncAction{
    Thread service(AsyncContext asyncCtx);
  }
  
  IAsyncAction f=(AsyncContext asyncCtx)->{
    return new Thread(()->{
      ServletRequest req=asyncCtx.getRequest();
      ServletResponse res=asyncCtx.getResponse();
      String sm=req.getParameter("m");
      String sn=req.getParameter("n");     
      long m=Long.parseLong(sm),n=Long.parseLong(sn);  
      long x=cmmdcService.cmmdc(m,n);
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
    });
  };
}  