package timer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet; 
import java.util.Enumeration;

@WebServlet(urlPatterns = "/timer") 

public class TimerServlet extends HttpServlet {
  private static MyTimer myTimer;
  //private static StringBuffer sb=new StringBuffer(1000);
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    PrintWriter out=res.getWriter();
    res.setContentType("text/html");
    /*
    out.println("<body>");
    out.println("<h1>TimerServlet</h1>");
    out.println("<p/>");
    */    
    String button="";
    for (Enumeration<String> e = req.getParameterNames(); e.hasMoreElements();){
      button=e.nextElement();
      System.out.println(button);
   
    
      switch(button){
        case "start":
          //System.out.println(button);
          String ss=req.getParameter("seconds");
          int seconds=Integer.parseInt(ss);
          StringBuffer sb=new StringBuffer(1000);
          myTimer=new MyTimer(seconds,out,sb);        
          break;
        case "stop":
          //System.out.println(button);
          myTimer.timerStopped();
          break; 
        case "state":
          out.println("<html>");
          out.println("<body>");
          out.println("<h1>TimerServlet</h1>");
          out.println("<p/>");    
          out.println(myTimer.getSb());
          out.println("<p/>");
          out.println( "<a href=\"index.html\">Start Page</a>");
          out.println("</body>");
          out.println("</html>");   
          break;        
      }
    }
    /*
    out.println("<p/>");
    out.println( "<a href=\"index.html\">Start Page</a>");
    out.println("</body>");
    out.println("</html>"); 
    */
    out.close();
  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    doGet(req,res);
  }
}
