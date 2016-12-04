package cmmdc.ejb;
import javax.ejb.Stateless;
import javax.ejb.Asynchronous;
import javax.ejb.AsyncResult;
import java.util.concurrent.Future;

@Stateless
public class CmmdcBean implements Cmmdc{
  @Asynchronous
  public Future<Long> cmmdc(long m, long n){
     long r,c;
     do{
        c=n;
        r=m%n;
        m=n;
        n=r;
     }while(r!=0);
     Long result=new Long(c);
     return new AsyncResult<Long>(result);
  }   
}