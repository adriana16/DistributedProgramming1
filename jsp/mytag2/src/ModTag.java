package jsp;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ModTag extends TagSupport{

String text;
boolean toUpperCase;

public void setText(String value){
	text=value;
}

public void setTrans(String value){
    toUpperCase=(new Boolean(value)).booleanValue();
}

public int doStartTag(){
try{
	JspWriter out=pageContext.getOut();
	if(toUpperCase)
		out.println(text.toUpperCase());
	else
	    out.println(text.toLowerCase());
}
catch(IOException e){
    System.out.println("ZiuaTagException "+e.getMessage());
}
return EVAL_BODY_INCLUDE;
}
}
 