import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 
import java.rmi.Naming;
import cmmdc.ICmmdc;

@WebServlet(urlPatterns = "/servletrmi") 

public class ServletRMI extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String sm=req.getParameter("m"),sn=req.getParameter("n");
    long m=(new Long(sm)).longValue(),n=(new Long(sn)).longValue();
    long x=0;
  
    String host=req.getParameter("host").trim();
    String sPort=req.getParameter("port");
    int port=Integer.parseInt(sPort);
    
    try{
      ICmmdc obj=(ICmmdc)Naming.lookup("//"+host+":"+port+"/CmmdcServer");
      x=obj.cmmdc(m,n);
    } 
    catch (Exception e) {
      System.out.println("CmmdcClient exception: "+e.getMessage());
    }
    String title="CmmdcServlet";
    res.setContentType("text/html");
    out.println("<HTML><HEAD><TITLT>");
    out.println(title);
    out.println("</TITLE></HEAD><BODY>");
    out.println("<H1>"+title+"</H1>");
    out.println("<P>Cmmdc is "+x);
    out.println("</BODY></HTML>");
    out.close();
  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    doGet(req,res);
  }
}
