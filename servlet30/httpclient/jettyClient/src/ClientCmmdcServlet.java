import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.Fields;
import java.util.Scanner;

public class ClientCmmdcServlet{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    String m=scanner.nextLine().trim();
    System.out.println("n=");
    String n=scanner.nextLine().trim();
    
    // Instantiate HttpClient
    HttpClient httpClient=new HttpClient();
    try{
      // Configure HttpClient
      
      httpClient.setFollowRedirects(false);
      // Start HttpClient
      httpClient.start();
    
      System.out.println("GET request");
      ContentResponse response=httpClient.newRequest("http://localhost:8080/myservlet/cmmdc")
        .method("GET")
        .param("m",m)
        .param("n",n)
        .param("tip","text/plain")
        .send();
      String result=response.getContentAsString();
      System.out.println("Cmmdc : "+result);    
      
      System.out.println("POST request");
      System.out.println("m=");
      m=scanner.nextLine().trim();
      System.out.println("n=");
      n=scanner.nextLine().trim();
    
      response=httpClient.POST("http://localhost:8080/myservlet/cmmdc")
        .param("m",m)
        .param("n",n)
        .param("tip","text/plain")
        .send();
      
      result=response.getContentAsString();
      System.out.println("Cmmdc : "+result);
      httpClient.stop();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
} 