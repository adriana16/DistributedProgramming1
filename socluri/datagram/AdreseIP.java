import java.net.InetAddress;
import java.net.UnknownHostException;


public class AdreseIP{
  public static void main(String arg[]){
    InetAddress adresa=null;
    try{
      adresa=InetAddress.getLocalHost();
      System.out.println("Calculatorul gazda are:");
      System.out.println("numele : "+adresa.getHostName());
      System.out.println("adresa IP : "+adresa.getHostAddress());
    }
    catch(UnknownHostException e){
      System.out.println("UnknownHostException : "+e.getMessage());
    }
    if(arg.length>0){
      for(int i=0;i<arg.length;i++){
        try{
          adresa=InetAddress.getByName(arg[i]);
          System.out.println("Calculatorul "+arg[i]+" are:");
          System.out.println("adresa IP \"getByName\" : "+adresa);
          byte []b=adresa.getAddress();
          for(int j=0;j<b.length;j++)
            if(b[j]<0) 
              System.out.print(256+b[j]+".");
            else
              System.out.print(b[j]+".");
          System.out.println();
        }
        catch(UnknownHostException e){
          System.out.println("UnknownHostException : "+
            e.getMessage());
        }
      }
    }      
  }
}