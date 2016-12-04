package cmmdc.client;
import javax.ejb.EJB;
import cmmdc.ejb.Cmmdc;
import java.util.Scanner;

public class CmmdcClient{
  @EJB
  private static Cmmdc cb; 
  
  public static void main(String[] args)throws Exception{
   
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    System.out.println("n=");
    long n=scanner.nextLong();
    
    //long m=Long.parseLong(args[0]);
    //long n=Long.parseLong(args[1]);
    long x=cb.cmmdc(m,n);
    System.out.println("Cmmdc : "+x);
  }
}