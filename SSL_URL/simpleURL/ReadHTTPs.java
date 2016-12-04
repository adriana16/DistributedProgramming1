import java.net.URL;
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

public class  ReadHTTPs{
   
  public static void main(String[] args){
    // Create a trust manager that does not validate certificate chains
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
      e.printStackTrace();
      //System.out.println("Error" + e.getMessage());
    }
    
    String adr="https://192.168.0.103:8443/url/Hello.html";
    //System.setProperty("http.proxyHost","10.3.5.133");
    //System.setProperty("http.proxyPort","3128");
    HttpsURLConnection conn=null;
    try{
      HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String hostName, SSLSession session) {
          System.out.println("Warning: URL Host: " + hostName + " vs. "
                  + session.getPeerHost());
          return true;
        }
      };
      URL url=new URL(adr);
      URLConnection con=url.openConnection();
      if(con instanceof HttpsURLConnection){
        conn=(HttpsURLConnection)con;
        conn.setHostnameVerifier(hv);
      }
    }
    catch(Exception e){
      e.printStackTrace();
      //System.out.println(e.getMessage());
    }  
    try(
      InputStream in=conn.getInputStream();
      InputStreamReader isr=new InputStreamReader(in);
      BufferedReader br=new BufferedReader(isr);
    ){
      String s;
      do{
        s=br.readLine();
        if(s!=null)
          System.out.println(s);
      }
      while(s!=null);
    }
    catch(Exception e){
      e.printStackTrace();
      //System.out.println(e.getMessage());
    }
  }
}
