package single.client;
import javax.naming.Context; 
import javax.naming.InitialContext;
import single.ejb.Single;
import java.util.Scanner;

public class SingleClient{
  private static Single sb; 
  
  public static void main(String[] args)throws Exception{
    Context ctx=null;
    try{
      ctx=new InitialContext();
      sb=(Single)ctx.lookup("java:global/single-ear/single-ejb/SingleBean");     
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
    }    
    Scanner scanner=new Scanner(System.in);
    String ch="Y";
    int index=0;
    
    while(ch.equals("Y")){
      index=sb.getIndex();
      System.out.println("Numarul de apelari : "+index);
      System.out.println("Continuati ? (Y/N)");
      do{
         ch=scanner.next().trim().toUpperCase();
      }
      while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
    }  
  }
}