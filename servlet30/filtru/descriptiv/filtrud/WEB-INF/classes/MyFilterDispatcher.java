import java.io.IOException; 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilterDispatcher implements Filter { 
  private FilterConfig filterConfig; 
  
  public void init(FilterConfig filterConfig) throws ServletException { 
    this.filterConfig = filterConfig; 
  } 
  
  public void destroy() {
    this.filterConfig = null; 
  } 
  
  public void doFilter(ServletRequest request, ServletResponse response, 
      FilterChain filterChain) throws IOException, ServletException{ 
    HttpServletRequest req = (HttpServletRequest) request; 
    HttpServletResponse res = (HttpServletResponse) response; 
    String uri = req.getRequestURI(); 
    System.out.println("Filter URI= "+uri);
    System.out.println(uri);
    
    if(uri.equals("/filtrud")||uri.equals("/filtrud/"))
      filterChain.doFilter(request, response);
    else{
      if(uri.equals("/filtrud/hello")){
        String dispatcherUri="/cmmdc.html";
        RequestDispatcher rd=request.getRequestDispatcher(dispatcherUri);
        rd.forward(request,response);
      }
      else{
        if(uri.equals("/filtrud/cmmdc")){        
          String tip=req.getParameter("tip");
          //System.out.println("tip="+tip);
          if(tip.equals("text/xml")){
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
          }
        }
        filterChain.doFilter(request, response);
      }   
    }
  }
}  
    