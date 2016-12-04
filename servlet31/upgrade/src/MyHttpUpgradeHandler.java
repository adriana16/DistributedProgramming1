package cmmdc;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletInputStream;
import javax.servlet.http.WebConnection;
import javax.servlet.http.HttpUpgradeHandler;

public class MyHttpUpgradeHandler implements HttpUpgradeHandler {
  private WebConnection wc = null;
 
  @Override
  public void init(WebConnection wc) {
    this.wc=wc;
    try{
      ServletInputStream input = wc.getInputStream();
      ServletOutputStream output=wc.getOutputStream();  
      String CRLF = "\r\n"; 
      String resStr = "update/1.0 " + CRLF;
      resStr += "Server: Glassfish/ServerTest" + CRLF;
      resStr += "Content-Type: text/html" + CRLF;
      resStr += "Connection: Upgrade" + CRLF;
      resStr += CRLF; 
      byte[] b=new byte[256];
      input.read(b);
      String data=new String(b).trim();
      String rez=solver(data); 
      resStr +=rez + CRLF;
      output.write(resStr.getBytes());
      output.flush();
    } 
    catch(Exception ex) {
        throw new RuntimeException(ex);
    }
  }

  @Override
  public void destroy() {
    try{
      wc.close();
    } 
    catch (Exception ex) {
      System.out.println("Destroy wc Exception : "+ex.getMessage());
    } 
  }
  
  private String solver(String data){
    System.out.println("Solver : "+data);
    String[] s=data.split(" ");
    String r="-1";
    try{       
      long m=Long.parseLong(s[0]);
      long n=Long.parseLong(s[1]);
      r=(new Long(cmmdc(m,n))).toString();
    }
    catch(NumberFormatException e){
      System.out.println("NumberFormatException : "+e.getMessage());
    }
    return "Cmmdc : "+r;
  } 
  
  private long cmmdc(long m, long n){
    long r,c;
    do{
      c=n;
      r=m%n;
      m=n;
      n=r;
    }while(r!=0);
    return c;
  }   
}
  
  