import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/nonblock"}, asyncSupported=true)
public class NonBlockingServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    ServletInputStream input = req.getInputStream();
    ServletOutputStream output = res.getOutputStream();
    AsyncContext context = req.startAsync();	  
    CmmdcListener listener=new CmmdcListener(input, context, output);  
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException {
    doGet(req, res);
  }
}
