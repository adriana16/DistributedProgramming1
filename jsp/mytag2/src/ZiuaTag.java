package jsp;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ZiuaTag extends TagSupport{
  String ziua;
  
  public void setZiua(String value){
    ziua=value;
  }
  
  public int doStartTag(){
  try{
    JspWriter out=pageContext.getOut();
    out.println(ziua);
  }
  catch(IOException e){
      System.out.println("ZiuaTagException "+e.getMessage());
  }
  return SKIP_BODY;
  }
}
 