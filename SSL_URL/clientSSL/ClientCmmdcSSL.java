import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.NameValuePair; 
import org.apache.http.message.BasicNameValuePair;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;

public class ClientCmmdcSSL{
  public final static void main(String[] args) throws Exception {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    try {
      KeyStore trustStore = 
        KeyStore.getInstance(KeyStore.getDefaultType());
      FileInputStream fis =new FileInputStream("tomcatKeystore"); 
      try {
        trustStore.load(fis,"1q2w3e".toCharArray());
      } 
      finally{
        try{ 
          fis.close();
        } 
        catch (Exception ignore) {}
      }

      SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
      Scheme scheme = new Scheme("https", 443, socketFactory);
      httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
      
      Scanner scanner=new Scanner(System.in);
      System.out.println("m=");
      String m=scanner.nextLine().trim();
      System.out.println("n=");
      String n=scanner.nextLine().trim();
      List<NameValuePair> qparams = new ArrayList<NameValuePair>();
      qparams.add(new BasicNameValuePair("m", m));
      qparams.add(new BasicNameValuePair("n", n));
      qparams.add(new BasicNameValuePair("tip", "text/plain"));
      URI uri = URIUtils.createURI("http", "localhost", 8080,
        "/myservlet/cmmdc",URLEncodedUtils.format(qparams,"UTF-8"),
        null);
      HttpGet httpget = new HttpGet(uri);
      
      System.out.println("executing request" + httpget.getRequestLine());

      HttpResponse response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();

      System.out.println("----------------------------------------");
      System.out.println(response.getStatusLine());
      if (entity != null) {
				InputStream is=entity.getContent();
				int l;
				byte[] tmp=new byte[2048];
				while((l=is.read(tmp))!=-1){}
				System.out.println("Cmmdc = "+(new String(tmp).trim()));
			}		
      //EntityUtils.consume(entity);
    } 
    finally {
      // When HttpClient instance is no longer needed,
      // shut down the connection manager to ensure
      // immediate deallocation of all system resources
      httpclient.getConnectionManager().shutdown();
    }
  }
}
