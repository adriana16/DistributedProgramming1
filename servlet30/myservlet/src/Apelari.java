package cookie;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/cookie") 

public class Apelari extends HttpServlet{

  public void doGet(HttpServletRequest  req,
                    HttpServletResponse res)
    throws ServletException,IOException{
    res.setContentType("text/html");
    String mesaj="";
    PrintWriter out=res.getWriter();
    Cookie myCookie=null;
    int contor=0;
    Cookie [] cookies=req.getCookies();
    boolean sw=false;
    if(cookies!=null){
       for(int i=0;i<cookies.length;i++){
          String name=cookies[i].getName();
          if(name.equals("urmarire")){           
             sw=true;
             contor=Integer.parseInt(cookies[i].getValue());
             contor++;   
             mesaj="Bine ati revenit !";
             System.out.println("Gasit "+contor);
          }
       }    
    }
    if(sw){
       myCookie=new Cookie("urmarire",(new Integer(contor)).toString());
    }
    else{    
       myCookie=new Cookie("urmarire", (new Integer(1)).toString());
       contor=1;
       mesaj="Salut !";
    }
    myCookie.setMaxAge(1000000);
    res.addCookie(myCookie);
    out.println("<html><body>");
    out.println("<h1>"+mesaj+"</h1>");
    out.println("Numarul de accesari al aceastei pagini este "+contor);
    out.println("</body></html>");
    out.close();
  }

  public void doPost(HttpServletRequest  req,
                     HttpServletResponse res)
  throws ServletException,IOException{
    doGet(req,res);
  }
}