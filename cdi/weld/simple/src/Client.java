import java.util.Scanner;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import cmmdc.ApelCmmdc;

public class Client{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    long m,n,r;
    System.out.println("m=");
    m=scanner.nextLong();
    System.out.println("n=");
    n=scanner.nextLong();   
    WeldContainer weld = new Weld().initialize();
    ApelCmmdc obj = weld.instance().select(ApelCmmdc.class).get();
    r=obj.compute(m,n);
    System.out.println("Cmmdc : "+r);
  }
}