package simple.app.cmmdc;

public class App{
  public static void main(String[] args){
    if(args.length<2){
      System.out.println("Usage: ");
      System.out.println("java simpleapp.cmmdc.App number1 number2");
      System.exit(0);
    }
    long m=Long.parseLong(args[0]);
    long n=Long.parseLong(args[1]);
    MyCmmdc obj=new MyCmmdc();
    System.out.println("cmmdc = "+obj.cmmdcService.cmmdc(m,n));
  }
}  