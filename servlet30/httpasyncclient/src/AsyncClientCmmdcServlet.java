import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.protocol.HttpContext; 

import java.nio.CharBuffer; 
import java.io.IOException;
import java.util.concurrent.Future; 

public class AsyncClientCmmdcServlet{
  static String uri="http://localhost:8080/myservlet/cmmdc";
	
  public static void main(String[] args) throws Exception {  
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    String m=scanner.nextLine().trim();
    System.out.println("n=");
    String n=scanner.nextLine().trim();
    String requestData="?m="+m+"&n="+n+"&tip=text/plain";
    System.out.println(requestData);
    // Create an instance of HttpAsyncClient.
    
    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault(); 
    httpclient.start();
    try {
      Future<Boolean> future = httpclient.execute(
        HttpAsyncMethods.createGet(uri+requestData),
        new MyResponseConsumer(), null);
      Boolean result = future.get();
      if (result != null && result.booleanValue()) {
        System.out.println("Request successfully executed");
      } 
      else {
        System.out.println("Request failed");
      }
      System.out.println("Shutting down");
    } 
    finally {
      httpclient.close();
    }
    System.out.println("Done");
  } 
  
  static class MyResponseConsumer extends AsyncCharConsumer<Boolean> {
    @Override
    protected void onResponseReceived(final HttpResponse response) {
    }

    @Override
    protected void onCharReceived(final CharBuffer buf, final IOControl ioctrl) throws IOException {
      System.out.println("Cmmdc : ");
      while (buf.hasRemaining()) {
        System.out.print(buf.get());
      }
    }

    @Override
    protected void releaseResources() {
    }

    @Override
    protected Boolean buildResult(final HttpContext context) {
      return Boolean.TRUE;
    }
  } 
}
