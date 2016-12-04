import java.util.Scanner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import service.ICmmdc;

public class Client{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    long m,n,r;
    System.out.println("m=");
    m=scanner.nextLong();
    System.out.println("n=");
    n=scanner.nextLong();
    Injector injector = Guice.createInjector(new AppModule() );    
    ICmmdc obj = injector.getInstance(ICmmdc.class);
    r=obj.cmmdc(m,n);
    System.out.println("Cmmdc : "+r);
  }
}