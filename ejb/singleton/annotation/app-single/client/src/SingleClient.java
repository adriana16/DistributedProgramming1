package single.client;
import javax.ejb.EJB;
import single.ejb.Single;
import java.util.Scanner;

public class SingleClient{
  @EJB
  private static Single nb; 
  
  public static void main(String[] args)throws Exception{
    Scanner scanner=new Scanner(System.in);
    String ch="Y";
    int index=0;
    
    while(ch.equals("Y")){
      index=nb.getIndex();
      System.out.println("Numarul de apelari : "+index);
      System.out.println("Continuati ? (Y/N)");
      do{
         ch=scanner.next().trim().toUpperCase();
      }
      while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
    }  
  }
}