import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
//import org.eclipse.jetty.client.api.Response.CompleteListener;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
//import org.eclipse.jetty.util.Fields;
import java.util.Scanner;

public class AsyncClientCmmdcServlet{
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
      //ContentResponse response=
      httpClient.newRequest("http://localhost:8080/myservlet/cmmdc")
        .method("GET")
        .param("m",m)
        .param("n",n)
        .param("tip","text/plain")
        .send(new BufferingResponseListener(8*1024*1024){
          @Override
          public void onComplete(Result result){
            if(! result.isFailed()){
              byte[] responseContent=getContent();
              System.out.println("Cmmdc : "+(new String(responseContent)));
            }
          }
        });
      Thread.sleep(1000);
      httpClient.stop();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  /*
  static class MyListener implements Response.CompleteListener{
    @Override
    public void onComplete(Result result){
      ContentResponse response=(ContentResponse)result.getResponse();
      String r=response.getContentAsString();
      System.out.println("Cmmdc : "+r);  
    }
  }
  */
} 