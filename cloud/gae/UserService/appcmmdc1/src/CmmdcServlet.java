import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

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
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      String tip=req.getParameter("tip");  
      if(tip==null){
        res.sendRedirect("/cmmdc.html");
      }  
      else{
        String sm=req.getParameter("m"),sn=req.getParameter("n");     
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
          out.println("Hello "+user.getNickname()+" !");
          out.println("<P>Cmmdc is "+x);
          out.println("<p/>");
          out.println("<a href=\"/cmmdc.html\">Reluare</a>");
          out.println("<p/>");
          String signOut=userService.createLogoutURL(req.getRequestURI());
          out.println("<a href=\""+signOut+"\">Sign out</a>");
          out.println("</BODY></HTML>");
        }
        else{
          res.setContentType("text/plain");
          out.println(x);
        }
        out.close();
      }   
    } 
    else{
      res.sendRedirect(userService.createLoginURL(req.getRequestURI()));
    }
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}