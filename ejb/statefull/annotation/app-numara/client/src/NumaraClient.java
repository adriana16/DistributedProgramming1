package numara.client;
import javax.ejb.EJB;
import numara.ejb.Numara;
import java.util.Scanner;

public class NumaraClient{
  @EJB
  private static Numara nb; 
  
  public static void main(String[] args)throws Exception{
    Scanner scanner=new Scanner(System.in);
    String ch="Y",msg="";
    int op,index=0;
    
    while(ch.equals("Y")){
      System.out.println("Operatii : 1. Numara 2. Sterge componenta EJB");
      System.out.println("Operatia : ");
      op=scanner.nextInt();
      switch(op){
        case 1:
          index=nb.getIndex();
          msg="Numarul de apelari : "+(new Integer(index)).toString();
          break;
        case 2:
          nb.remove();
          msg="S-a sters componenta EJB";
          break;
        default:
          msg="Cod operatie eronat";
          break;
      }     
      System.out.println(msg);
      
      System.out.println("Continuati ? (Y/N)");
      do{
         ch=scanner.next().trim().toUpperCase();
      }
      while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
    }  
  }
}