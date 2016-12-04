package cmmdc.client;
import javax.naming.Context; 
import javax.naming.InitialContext; 
import cmmdc.ejb.Cmmdc;
import java.util.Scanner;

public class CmmdcClient{
  private static Cmmdc cb; 
  
  public static void main(String[] args)throws Exception{
    Context ctx=null;
    try{
      ctx=new InitialContext();
      cb=(Cmmdc)ctx.lookup("java:global/cmmdc-ear/cmmdc-ejb/CmmdcBean");     
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
    }    
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    System.out.println("n=");
    long n=scanner.nextLong();
    long x=cb.cmmdc(m,n);
    System.out.println("Cmmdc : "+x);
  }
}