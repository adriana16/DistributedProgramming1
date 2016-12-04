package cmmdc.ejb;
import javax.ejb.Stateless;
//import javax.ejb.Remote;

@Stateless
//@Remote(Cmmdc.class)
public class CmmdcBean implements Cmmdc{
  public long cmmdc(long m, long n){
     long r,c;
     do{
        c=n;
        r=m%n;
        m=n;
        n=r;
     }while(r!=0);
     return c;
  }   
}