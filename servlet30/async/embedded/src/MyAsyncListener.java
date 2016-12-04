package listeners;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletRequest;

public class MyAsyncListener implements AsyncListener{
  
  public MyAsyncListener(){}
  
  public void onComplete(AsyncEvent ae){
     ServletRequest req=ae.getAsyncContext().getRequest();
     String r=req.getParameter("m")+" <-> "+req.getParameter("n");
     System.out.println("AsyncListener: onComplete for request: "+r);
  }      
  
  public void onTimeout(AsyncEvent ae){
     ServletRequest req=ae.getAsyncContext().getRequest();
     String r=req.getParameter("m")+" <-> "+req.getParameter("n");
     //logger.info("AsyncListener: onTimeout for request: "+ae.getAsyncContext().getRequest().getParameter("id"));
     System.out.println("AsyncListener: onTimeout for request: "+r);
  } 

  public void onError(AsyncEvent ae){
     ServletRequest req=ae.getAsyncContext().getRequest();
     String r=req.getParameter("m")+" <-> "+req.getParameter("n");
     //logger.info("AsyncListener: onError for request: "+ae.getAsyncContext().getRequest().getParameter("id"));
     System.out.println("AsyncListener: onError for request: "+r);
  }

  public void onStartAsync(AsyncEvent ae){
     ServletRequest req=ae.getAsyncContext().getRequest();
     String r=req.getParameter("m")+" <-> "+req.getParameter("n");
    // logger.info("AsyncListener: onStartAsync for request: "+ae.getAsyncContext().getRequest().getParameter("id"));
     System.out.println("AsyncListener: onStartAsync for request: "+r);
  }        
}