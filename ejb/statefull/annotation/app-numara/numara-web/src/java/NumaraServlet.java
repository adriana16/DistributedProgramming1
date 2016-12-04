package numara.web;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import numara.ejb.Numara;
import javax.ejb.EJB; 
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/numara") 
public class NumaraServlet extends HttpServlet{
  @EJB
  Numara nb;
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    String oper=req.getParameter("oper");
    PrintWriter out=res.getWriter();
    res.setContentType("text/html");
    String title="Numara Servlet";
    out.println("<HTML><HEAD><TITLE>");
    out.println(title);
    out.println("</TITLE></HEAD><BODY>");
    out.println("<H1>"+title+"</H1>");
    switch(oper){ 
      case "index" :
         int x=nb.getIndex();
         out.println("<p>Valoarea index : "+x);
         break;
      case "exit" :
         out.println("<p>OK: Pe raspunderea d-voastra");
         nb.remove();
         break;
    }   
    out.println("</BODY></HTML>");    
    out.close();   
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}