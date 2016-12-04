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
import javax.servlet.annotation.WebFilter; 

@WebFilter(filterName="MyFilterDispatcher",urlPatterns={"/*"})

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
    if(uri.equals("/filtrup")||uri.equals("/filtrup/"))
      filterChain.doFilter(request, response);
    else{
      if(uri.equals("/filtrup/hello")){
        String dispatcherUri="/cmmdc.html";
        RequestDispatcher rd=request.getRequestDispatcher(dispatcherUri);
        rd.forward(request,response);
      }
      else{
        if(uri.equals("/filtrup/cmmdc")){        
          String tip=req.getParameter("tip");
          if(tip.equals("text/xml")){
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
          }
        }
        filterChain.doFilter(request, response);
      }   
    }
  }
}  
    