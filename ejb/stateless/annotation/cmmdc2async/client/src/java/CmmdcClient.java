package cmmdc.client;
import javax.ejb.EJB;
import cmmdc.ejb.Cmmdc;
import java.util.Scanner;
import java.util.concurrent.Future;

public class CmmdcClient{
  @EJB
  private static Cmmdc cb; 
  
  public static void main(String[] args)throws Exception{   
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    long m=scanner.nextLong();
    System.out.println("n=");
    long n=scanner.nextLong();
    Future<Long> result=cb.cmmdc(m,n);
    long x=0;
    try{
      while(! result.isDone()){;}; 
      x=result.get().longValue();
    }
    catch(Exception e){
      e.printStackTrace();
    }    
    System.out.println("Cmmdc : "+x);
  }
}