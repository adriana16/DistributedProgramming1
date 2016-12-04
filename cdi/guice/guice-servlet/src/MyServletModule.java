import com.google.inject.servlet.ServletModule;

public class MyServletModule extends ServletModule{  
  protected void configureServlets(){
    serve("/cmmdc").with(CmmdcGuiceServlet.class);
  } 
}