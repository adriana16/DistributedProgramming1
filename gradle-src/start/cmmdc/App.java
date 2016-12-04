package simple.app.cmmdc;

public class App{
  public static void main(String[] args){
    long m=Long.parseLong(args[0]);
    long n=Long.parseLong(args[1]);
    MyCmmdc obj=new MyCmmdc();
    System.out.println("cmmdc = "+obj.cmmdcService.cmmdc(m,n));
  }
}  