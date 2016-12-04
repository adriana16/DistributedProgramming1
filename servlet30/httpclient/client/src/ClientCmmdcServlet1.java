import java.util.Scanner;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class ClientCmmdcServlet1{
  static String uri="http://localhost:8080/myservlet/cmmdc";
	
  public static void main(String[] args) {  
    System.out.println("ClientCmmdcServlet1");  
    System.out.println("Varianta fluenta\n");  
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    String m=scanner.nextLine().trim();
    System.out.println("n=");
    String n=scanner.nextLine().trim();

    /*
    Form form=Form.form()
       .add("m",m)
       .add("n",n)
       .add("tip","text/plain");
    */
    System.out.println("Post method request");
    try{
      String result=Request.Post(uri)
        .bodyForm(Form.form()
           .add("m",m)
           .add("n",n)
           .add("tip","text/plain")
           .build())
        //.bodyForm(form.build())
        .execute().returnContent().asString();
      System.out.println("Cmmdc = "+result);
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
    
    System.out.println("Get method request");
    String data="?m="+m+"&n="+n+"&tip=text/plain";
    try{
      String result=Request.Get(uri+data)
        .execute().returnContent().asString();
      System.out.println("Cmmdc = "+result);
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
  }
}
