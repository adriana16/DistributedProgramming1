import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class  ReadHTTP{
  public static void main(String[] args){
    String adr="http://localhost:8080/url/Hello.html";
    //System.setProperty("http.proxyHost","10.3.5.133");
    //System.setProperty("http.proxyPort","3128");
    URL url=null;
    try{
      url=new URL(adr);
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }  
    try(
      InputStream in=url.openStream();
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
      System.out.println(e.getMessage());
    }
  }
}
