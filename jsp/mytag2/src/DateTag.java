package jsp;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;

public class DateTag extends TagSupport{
   @Override
   public int doStartTag(){
      try{
         JspWriter out=pageContext.getOut();
         out.println(new Date());
      }
      catch(IOException e){
         System.out.println("DateTagException "+e.getMessage());
      }
      return SKIP_BODY;
   }
}
 