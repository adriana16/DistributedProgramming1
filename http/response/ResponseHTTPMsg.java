import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ResponseHTTPMsg{
  public static void main(String[] args){
    String reqGET="GET /apphello/hello?name=mk&tip=text%2Fhtml HTTP/1.1\r\n"+
    "Host: localhost:8080\r\n"+
    "Connection: keep-alive\r\n"+
    "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.106 Safari/535.2\r\n"+
    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"+
    "Accept-Encoding: gzip,deflate,sdch\r\n"+
    "Accept-Language: en-US,en;q=0.8\r\n"+
    "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n";

    String reqPOST="POST /apphello/hello HTTP/1.1\r\n"+
    "Host: localhost:8080\r\n"+
    "Connection: keep-alive\r\n"+
    "Content-Length: 23\r\n"+
    "Cache-Control: max-age=0\r\n"+
    "Origin: null\r\n"+
    "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.106 Safari/535.2\r\n"+
    "Content-Type: application/x-www-form-urlencoded\r\n"+
    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"+
    "Accept-Encoding: gzip,deflate,sdch\r\n"+
    "Accept-Language: en-US,en;q=0.8\r\n"+
    "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n"+
    "\r\n"+
    "name=mk&tip=text%2Fhtml";

    Scanner scanner=new Scanner(System.in);
    System.out.println("Precizati metoda HTTP :");
    System.out.println("1 : GET; 2: POST");
    int metoda;
    do{
      metoda=scanner.nextInt();
    }  
    while((metoda!=1)&&(metoda!=2));  
    try(
      Socket socket=new Socket("localhost",8080);
      InputStreamReader isr=new InputStreamReader(socket.getInputStream());
      BufferedReader br=new BufferedReader(isr);
      FileWriter fw=new FileWriter("output.txt",true);
      BufferedWriter bw=new BufferedWriter(fw);
      PrintWriter pw=new PrintWriter(socket.getOutputStream(),true)
    ){
      switch(metoda){
        case 1:
          //Lansarea unei cereri GET
          pw.println(reqGET);
          break;
        case 2:   
          //Lansarea unei cereri POST
          pw.println(reqPOST);
      }    
      for(;;){
        String s=br.readLine();
        if(s==null)
           break;
        else{
          System.out.println(s);
          bw.write(s);
          bw.newLine();
          bw.flush();
        }  
      }  
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
   
  }
}
