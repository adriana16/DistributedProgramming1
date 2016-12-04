package simple.app.cmmdc;
import java.util.Scanner;

public class App{
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    System.out.println("n=");
    long n=scanner.nextLong();
    MyCmmdc obj=new MyCmmdc();
    System.out.println("cmmdc = "+obj.cmmdcService.cmmdc(m,n));
  }
}  