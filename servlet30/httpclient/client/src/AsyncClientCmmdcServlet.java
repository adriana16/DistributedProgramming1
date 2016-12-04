import java.util.Scanner;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.http.concurrent.FutureCallback;//HttpCore

public class AsyncClientCmmdcServlet{
  static String uri="http://localhost:8080/myservlet/cmmdc";
	
  public static void main(String[] args) { 
    System.out.println("AsyncClientCmmdcServlet");  
    System.out.println("Varianta asincrona\n");    
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    String m=scanner.nextLine().trim();
    System.out.println("n=");
    String n=scanner.nextLine().trim();

    ExecutorService threadpool = Executors.newFixedThreadPool(2); 
    Async async = Async.newInstance().use(threadpool); 
    
    System.out.println("Post method request");
    try{
      Request request=Request.Post(uri)
        .bodyForm(Form.form()
           .add("m",m)
           .add("n",n)
           .add("tip","text/plain")
           .build());
        
      //Future<Content> future=async.execute(request);
      //while(!future.isDone()){;}      
      //System.out.println("Cmmdc = "+future.get().asString());
      
      Future<Content> future=async.execute(request, 
          new FutureCallback<Content>(){
        @Override
        public void completed(final Content content){
          System.out.println("Cmmdc : "+content.asString());
        }
        @Override
        public void failed(final Exception e) {
            System.out.println(e.getMessage() + ": " + request);
        }
        @Override
        public void cancelled() {}  
      });
      while(!future.isDone()){;}      
      
      threadpool.shutdown();
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
  }
}
