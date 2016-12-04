package myservlet;
//@javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Default;

@Default
class CmmdcAction{
  
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