import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 
import ejb.AgendaEMail;
import javax.ejb.EJB; 
import java.util.List;
import java.util.Iterator;
import entity.Adrese;

@WebServlet(urlPatterns = "/adrese")
public class AgendaEMailServlet extends HttpServlet {
  @EJB
  AgendaEMail agendae;
  private static List<Adrese> list=null;

  public void doGet(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    String myAtribut,myVal;
    res.setContentType("text/html");
    ServletOutputStream out = res.getOutputStream();  
    myAtribut=req.getParameter("criteriu");
    myVal=req.getParameter("termen");
    if(myAtribut.equals("nume")){
      list=agendae.getEmail(myVal);
    }
    else{
      list=agendae.getNume(myVal);
    }
    Iterator<Adrese> iter=list.iterator();
    out.println("<html>");
    out.println("<head><title>AgendaEMail</title></head>");
    out.println("<body>");
    out.println("<h1>Agenda de Adrese e-mail </h1>");
    out.println("<p/>"); 
    out.println("<b>    Nume    <-->    Adresa e-mail  </b>");
    out.println("<br/>");
    while(iter.hasNext()){
      Adrese inreg=(Adrese)iter.next();
      out.println(inreg.getNume()+" <-> "+inreg.getEmail());
      out.println("<br/>");     
    }  
    out.println("</body>");
    out.println("</html>");
    out.close();  
  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  }
}
