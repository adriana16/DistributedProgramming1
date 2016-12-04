import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient; 

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.protocol.BasicHttpContext;

import java.util.List;
import java.util.ArrayList;
import org.apache.http.NameValuePair; 
import org.apache.http.message.BasicNameValuePair; 

import org.apache.http.client.entity.UrlEncodedFormEntity;
import java.io.*;

public class ClientCmmdcServlet{
  
	static private String username="digest";
  static private String password="digest";
  static private String host="localhost";
  static private String port="8080";
  static private String uri="http://"+host+":"+port+"/cmmdc/cmmdc";
  
  public static void main(String[] args){  
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    String m=scanner.nextLine().trim();
    System.out.println("n=");
    String n=scanner.nextLine().trim();
    
    // Crearea unei instante HttpClient.
    DefaultHttpClient httpclient = new DefaultHttpClient();  
    // Activitati pentru autentificare
    httpclient.getCredentialsProvider().setCredentials(
      new AuthScope(host,Integer.parseInt(port)),
      new UsernamePasswordCredentials(username,password)
    );
    BasicHttpContext context=new BasicHttpContext();
    DigestScheme schema = new DigestScheme();
    context.setAttribute("preemptive-auth", schema);
    
    // Crearea metodei de acces la servlet
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
    qparams.add(new BasicNameValuePair("m", m));
    qparams.add(new BasicNameValuePair("n", n));
    qparams.add(new BasicNameValuePair("tip", "text/plain"));
		try{
			UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
			HttpPost httppost=new HttpPost(uri);
			httppost.setEntity(params);
      // Apelarea servlet-ului
			HttpResponse response=httpclient.execute(httppost,context);
			HttpEntity entity=response.getEntity();
			if(entity!=null){
				InputStream is=entity.getContent();
				int l;
				byte[] tmp=new byte[2048];
				while((l=is.read(tmp))!=-1){}
				System.out.println("Cmmdc = "+(new String(tmp).trim()));
			}		
	  }
		catch(Exception e){
		  System.out.println("Exception : "+e.getMessage());
		}
  }
}
