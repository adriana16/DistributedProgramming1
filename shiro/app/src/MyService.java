package app;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MyService{
  /*
  public static void main(String[] args){
    MyService app=new MyService();
    app.service();
  }
  */
  
  public void service(){
    String ch="Y";
    int a=0;
    Scanner scanner=new Scanner(System.in);
    while(ch.startsWith("Y")){
      do{
        System.out.println("Continue ? (Y/N)");
        ch=scanner.next().toUpperCase();
      }
      while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
      if(ch.startsWith("Y")){
        System.out.println("Oferta:");
        System.out.println("1: Cmmdc");
        System.out.println("2: Helloname");
        do{
          a=0;
          try{
            a=scanner.nextInt();
          }
          catch(InputMismatchException e){}  
        }
        while((a<1)&&(a>2));
        switch(a){
          case 1:
            System.out.println("m=");
            long m=scanner.nextLong();
            System.out.println("n=");
            long n=scanner.nextLong();
            Cmmdc o1=new Cmmdc();
            long r1=o1.cmmdcService.cmmdc(m,n);
            System.out.println("Cmmdc: "+r1);
            break;
          case 2:
            System.out.println("nume : ");
            String nume=scanner.next();
            Helloname o2=new Helloname();
            String r2=o2.sayHello(nume);
            System.out.println(r2);
            break;
        }
      }
    }
  }
}