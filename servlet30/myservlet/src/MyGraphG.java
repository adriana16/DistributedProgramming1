package graphgif;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet; 

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/graphgif") 

public class MyGraphG extends HttpServlet { 
  public void doGet(HttpServletRequest req, HttpServletResponse res)
  		throws ServletException, IOException {
    String fs=System.getProperty("file.separator");
    ServletOutputStream out = res.getOutputStream();
    /*
    String pathTomcat = new java.io.File(".").getCanonicalPath();
    String contextPath=req.getContextPath();
    Path path=Paths.get(pathTomcat+fs+"webapps"+fs+contextPath+fs+"walking_santa.gif");
    */
    String pathApp=req.getSession().getServletContext().getRealPath("/")+fs;    
    Path path=Paths.get(pathApp+"walking_santa.gif");
    try{
      res.setContentType("image/gif");
      Files.copy(path,out);
    }
    catch(Exception e){
      res.setContentType("text/plain"); 
      System.out.println(e.getMessage());
      out.println("Cererea d-voastra nu poate fi satisfacuta");
    }      
    out.close();
  }
}