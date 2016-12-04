package graphjpg;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet; 
import javax.imageio.ImageIO;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@WebServlet(urlPatterns = "/graphjpg") 

public class MyGraphP extends HttpServlet { 

  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException, IOException {
    String fs=System.getProperty("file.separator");
    res.setContentType("text/html");
    PrintWriter out=res.getWriter();
    String pathTomcat = new java.io.File(".").getCanonicalPath();
    String contextPath=req.getContextPath();
  
    String fileRef=pathTomcat+fs+"webapps"+fs+contextPath+fs;
    String numeFis="desen";
    String ext="jpg";  // sau "png"
    // Formarea imaginii 
    Frame frame = null;
    Graphics g = null;
    BufferedImage image=null;
    try{
      frame = new Frame();
      frame.addNotify();
      image = (BufferedImage)frame.createImage(800, 60);
      g = image.getGraphics();
      // Fixarea fontului
      g.setFont(new Font("Serif", Font.ITALIC, 48));
      // Editarea unui text
      g.drawString(" Tehnologii distribuite in Java", 10, 50);
       
      // Salvarea imaginii intr-un fisier jpg sau png
    
      File f=new File(fileRef+numeFis+"."+ext);
      ImageIO.write(image,"jpg",f);
    
      // Raspunsul catre client
      out.println("<HTML><BODY>");
      out.println("<h2>Imagine preluata de pe server </h2>");
      out.println("<p><a href=\"http://localhost:8080/myservlet/"+
        numeFis+"."+ext+"\">");
      out.println("Vizualizarea imaginii </a>");
      out.println("</BODY></HTML>");
      out.close();
    }
    finally {
      // Eliberarea resurselor
      if (g != null) g.dispose();
      if (frame != null) frame.removeNotify();
    }
  }
}
