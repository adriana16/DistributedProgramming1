package inlantuite;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/calcul") 

public class ComputeServlet extends HttpServlet{

  public long cmmdc(long m, long n){
    long r,c;
    do{
      c=n;
      r=m%n;
      m=n;
      n=r;
    }
    while(r!=0);
    return c;
  }   
   
  public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    long m=Long.parseLong(req.getParameter("m"));
    long n=Long.parseLong(req.getParameter("n"));   
    PrintWriter out=res.getWriter();
    out.println("<H1> Cmmdc = "+cmmdc(m,n)+"</H1>");
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
     doGet(req,res);
  }
}