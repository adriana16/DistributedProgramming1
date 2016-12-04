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

@WebServlet(urlPatterns = "/download")

public class DownloadServlet extends HttpServlet {
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    ServletOutputStream out=res.getOutputStream(); 
    String file=req.getParameter("file");
    System.out.println(file);
    Path cale=Paths.get("webapps/download/resources/"+file);
    try{
      System.out.println(cale+file);
      res.setContentType("Application/Octet-stream"); 
      res.addHeader("Content-Disposition", "attachment; filename="+ file);
      Files.copy(cale,out);
    }
    catch(Exception e){
      res.setContentType("text/plain"); 
      out.println("Cererea d-voastra nu poate fi satisfacuta");
    }      
    out.close();
  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    doGet(req,res);
  }
}
