package inlantuite;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/verif") 

public class VerifServlet extends HttpServlet{

  public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    PrintWriter out=res.getWriter();
    res.setContentType("text/html");
    String sm=req.getParameter("m"),sn=req.getParameter("n");
    //System.out.println(sm+" "+sn);
    String message="";
    long m,n;
    if((sm==null)||(sm.equals(""))){
       message="Numar absent";
    }
    else{
      try{
        m=Long.parseLong(sm);
      }
      catch(NumberFormatException e){
        message="Nu este numar";
      }
    }  
    if((sn==null)||(sn.equals(""))){
      message="Numar absent";
    }
    else{
      try{
        n=Long.parseLong(sn);
      }
      catch(NumberFormatException e){
        message="Nu este numar";
      }
    }
    //System.out.println(message);
    out.println("<html><body>");
    if(message.equals("")){
      out.println("<h3> Rezultatul ob&#355;inut </h3>");
      RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/calcul");
      if(dispatcher!=null)
        dispatcher.include(req,res);
    }
    else{
      out.println("<h3> Date eronate </h3>");
      out.println(message);
    }
    out.println("</body></html>");
    out.close();
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    doGet(req,res);
  }
}