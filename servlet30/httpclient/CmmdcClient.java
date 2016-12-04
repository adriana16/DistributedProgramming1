import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class CmmdcClient{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    System.out.println("n=");
    long n=scanner.nextLong();
    String msg="m="+m+"&n="+n+"&tip=text/plain";
    System.out.println("HttpURLConnection cu metoda GET");
    try{
      String urlGET="http://localhost:8080/myservlet/cmmdc?"+msg;
      URL url=new URL(urlGET);
      HttpURLConnection conn=(HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      System.out.println(conn.getResponseCode());
      System.out.println(conn.getResponseMessage());
      BufferedReader br=
         new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
    System.out.println("HttpURLConnection cu metoda POST");    
    try{
      String urlPOST="http://localhost:8080/myservlet/cmmdc";
      URL url=new URL(urlPOST);
      HttpURLConnection conn=(HttpURLConnection)url.openConnection();
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
      BufferedReader br=
         new BufferedReader(new InputStreamReader(conn.getInputStream()));
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