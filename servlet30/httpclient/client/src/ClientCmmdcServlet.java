import java.util.Scanner;
import org.apache.http.HttpEntity;

import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse; 
import org.apache.http.impl.client.HttpClients; 
import java.util.List;
import java.util.ArrayList;
import org.apache.http.NameValuePair; 
import org.apache.http.message.BasicNameValuePair; 

import org.apache.http.client.entity.UrlEncodedFormEntity;
import java.io.*;

public class ClientCmmdcServlet{
  static String uri="http://localhost:8080/myservlet/cmmdc";
	
  public static void main(String[] args) { 
    System.out.println("ClientCmmdcServlet");  
    System.out.println("Varianta standard\n");  
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    String m=scanner.nextLine().trim();
    System.out.println("n=");
    String n=scanner.nextLine().trim();

    // Create an instance of HttpClient.
    CloseableHttpClient httpclient = HttpClients.createDefault();
   
    // Create a method instance.
    List<NameValuePair> qparams = new ArrayList<NameValuePair>();
    qparams.add(new BasicNameValuePair("m", m));
    qparams.add(new BasicNameValuePair("n", n));
    qparams.add(new BasicNameValuePair("tip", "text/plain"));
    try{
      UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
      HttpPost httppost=new HttpPost(uri);
      httppost.setEntity(params);
      long mom0=System.currentTimeMillis();
      CloseableHttpResponse response=httpclient.execute(httppost);
      HttpEntity entity=response.getEntity();
      if(entity!=null){
        InputStream is=entity.getContent();
        int l;
        byte[] tmp=new byte[2048];
        while((l=is.read(tmp))!=-1){}
        long d=System.currentTimeMillis()-mom0;
        System.out.println("Durata : "+d);
        System.out.println("Cmmdc = "+(new String(tmp).trim()));
      }		
    }
    catch(Exception e){
    System.out.println("Exception : "+e.getMessage());
    }
  }
}
