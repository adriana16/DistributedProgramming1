import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 
import agendae.ejb.Agendae;
import javax.ejb.EJB; 

@WebServlet(urlPatterns = "/adrese")
public class AgendaEMailServlet extends HttpServlet {
  @EJB
  Agendae agendae;

  public void doGet(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    String myAtribut,myVal;
    res.setContentType("text/html");
    ServletOutputStream out = res.getOutputStream();  
    myAtribut=req.getParameter("criteriu");
    myVal=req.getParameter("termen");
    String result=agendae.getResult(myAtribut,myVal);
    
    out.println("<html>");
    out.println("<head><title>AgendaEMail</title></head>");
    out.println("<body>");
    out.println("<h1>Agenda de Adrese e-mail </h1>");
    out.println("<p/>"); 
    out.println("<b>    Nume    <-->    Adresa e-mail  </b>");
    out.println("<br/>");
    if((result!=null) && (!result.equals(""))){
      String[] s=result.split(" ");
      for(int i=0;i<s.length;i++){
        String[] ss=s[i].split("<->");
        out.println(ss[0]+" <-> "+ss[1]);
        out.println("<br/>");
      }
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
