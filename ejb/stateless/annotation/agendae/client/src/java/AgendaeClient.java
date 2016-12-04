package agendae.client;
import javax.ejb.EJB;
import agendae.ejb.Agendae;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AgendaeClient{
  @EJB
  private static Agendae agendae; 
  
  public static void main(String[] args)throws Exception{
    Scanner scanner=new Scanner(System.in);
    String ch="Y";
    int prel;
    String criteriu="",val=""; 
    while(ch.startsWith("Y")){
      do{
        System.out.println("Continue ? (Y/N)");
        ch=scanner.next().toUpperCase();
      }
      while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
      if(ch.startsWith("Y")){
        System.out.println("Natura interogarii ?");
        System.out.println("(Dupa nume:1,  Dupa email:2)");
        do{
          prel=0;
          try{
            prel=scanner.nextInt();
          }
          catch(InputMismatchException e){}
        }
        while((prel<1)&&(prel>2));
        switch(prel){
          case 1 :
            criteriu="nume";
            System.out.println("Numele");              
            val=scanner.next().trim();  
            break;
          case 2 :
            criteriu="email";
            System.out.println("Email");              
            val=scanner.next().trim();  
            break;
          default: System.out.println("Comanda eronata");
        }
        String result=agendae.getResult(criteriu,val);
        if((result!=null) && (!result.equals(""))){
          String[] s=result.split(" ");
          for(int i=0;i<s.length;i++){
            String[] ss=s[i].split("<->");
            System.out.println(ss[0]+" <-> "+ss[1]);
          }
        }  
        else{
          System.out.println("No item found !");
        }           
      }
    }
  }
}