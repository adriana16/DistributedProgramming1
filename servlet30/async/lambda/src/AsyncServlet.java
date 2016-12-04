package myservlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.AsyncContext;
import java.io.IOException;
import listeners.MyAsyncListener;

@WebServlet(urlPatterns="/cmmdc",asyncSupported=true)
public class AsyncServlet extends HttpServlet{
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    final AsyncContext asyncCtx=req.startAsync(req,res);
    AsyncAction asyncAction=new AsyncAction();
    asyncCtx.addListener(new MyAsyncListener());
    asyncCtx.start(asyncAction.f.service(asyncCtx));
  }  
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    doGet(req,res);
  }
}

