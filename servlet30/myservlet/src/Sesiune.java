package session;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/sesiune") 

public class Sesiune extends HttpServlet{
  public void doGet(HttpServletRequest  req,
                    HttpServletResponse res)
  throws ServletException,IOException{
  res.setContentType("text/html");
  String mesaj="";
  PrintWriter out=res.getWriter();
  HttpSession session=req.getSession(true);
  Integer contor=(Integer)session.getAttribute("noAcces");
  if(contor==null){
    contor=new Integer(1);
    mesaj="Salut !";
  }
  else{
    contor=new Integer(contor.intValue()+1);
    mesaj="Bine ati revenit !";
  }
  session.setAttribute("noAcces",contor);
  out.println("<html><body>");
  out.println("<h1>"+mesaj+"</h1>");
  out.println("Numarul de accesari al aceastei pagini este "+contor.intValue());
  out.println("</body></html>");
  out.close();
  }

  public void doPost(HttpServletRequest  req,
                     HttpServletResponse res)
  throws ServletException,IOException{
    doGet(req,res);
  }
}