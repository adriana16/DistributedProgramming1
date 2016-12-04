//import javax.naming.*;
import ejb.AgendaEMail;
import javax.ejb.EJB; 
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
import java.util.InputMismatchException;
import entity.Adrese;

public class Client {
  @EJB//(name="ejb/agendae")
  private static AgendaEMail bean;
  private static List<Adrese> list=null;

	public static void main(String[] args) {
    int prel,no;
    String ch="Y",nume="",email="";
    Scanner scanner=new Scanner(System.in);
    Iterator<Adrese> iter=null;
    Adrese inreg=null;
    
		try {
      while(ch.startsWith("Y")){
        do{
          System.out.println("Continue ? (Y/N)");
          ch=scanner.next().toUpperCase();
        }
        while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
        if(ch.startsWith("Y")){
          System.out.println("Natura interogarii ?");
          System.out.println("(Dupa nume:1,Dupa email:2)");
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
              System.out.println("Numele");
              nume=scanner.next().trim();
              list=bean.getEmail(nume);
              iter=list.iterator();
              System.out.println("Adresele de email pentru : "+nume);
              while(iter.hasNext()){
                inreg=(Adrese)iter.next();
                System.out.println(inreg.getEmail());
              }
              break;
            case 2 :
              System.out.println("Email");
              email=scanner.next().trim();
              list=bean.getNume(email);
              iter=list.iterator();
              System.out.println("Inregistrarile pentru adresa : "+email);
              while(iter.hasNext()){
                inreg=(Adrese)iter.next();
                System.out.println(inreg.getNume());
              }
              break;
            default: System.out.println("Comanda eronata");
          }
        }
      }

      //bean.createCustomers();
		} 
    catch (Exception e) {
			e.printStackTrace();
		}
	}
}  