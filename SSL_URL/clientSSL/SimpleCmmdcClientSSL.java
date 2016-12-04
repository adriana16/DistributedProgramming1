import java.net.URL;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SimpleCmmdcClientSSL{
  public static void main(String[] args){
    TrustManager[] trustManager = new TrustManager[]{
      new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void checkClientTrusted(
          java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(
          java.security.cert.X509Certificate[] certs, String authType) {
        }
      }
    };
    // Install the all-trusting trust manager
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null,trustManager,new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } 
    catch (Exception e) {
      System.out.println("Error" + e.getMessage());
    }
    
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    System.out.println("n=");
    long n=scanner.nextLong();
    String msg="m="+m+"&n="+n+"&tip=text/plain"; 
      
    HostnameVerifier hv=null;
    try{
      hv = new HostnameVerifier() {
        public boolean verify(String hostName, SSLSession session) {
          System.out.println("Warning: URL Host: " + hostName + " vs. "
                  + session.getPeerHost());
          return true;
        }
      };
      
           
      String urlGET="https://localhost:8443/myservlet/cmmdc?"+msg;
      
      URL url=new URL(urlGET);
      HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
      conn.setHostnameVerifier(hv);
      conn.setRequestMethod("GET");
      System.out.println(conn.getResponseCode());
      System.out.println(conn.getResponseMessage());
      BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String s;
      while((s=br.readLine())!=null){
        System.out.println(s);
      }
      br.close();
      conn.disconnect();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    } 
    
    System.out.println("m=");
    m=scanner.nextLong();
    System.out.println("n=");
    n=scanner.nextLong();
    msg="m="+m+"&n="+n+"&tip=text/plain";
    
    try{
      String urlPOST="https://localhost:8443/myservlet/cmmdc";
      URL url=new URL(urlPOST);
      HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
      conn.setHostnameVerifier(hv);
      conn.setRequestMethod("POST");
      conn.setUseCaches(false);
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
      PrintWriter pw=new PrintWriter(conn.getOutputStream());
      
      pw.println(msg);
      pw.flush();
      System.out.println(conn.getResponseCode());
      System.out.println(conn.getResponseMessage());
      BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String s;
      while((s=br.readLine())!=null){
        System.out.println(s);
      }
      br.close();
      pw.close();
      conn.disconnect();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }          
  }
}  