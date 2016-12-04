package simple.app.cmmdc;
public class MyCmmdc{
  /*
  private long m,n;
  MyCmmdc(long m, long n){
    this.m=m;
    this.n=n ;
  }
  
  public long cmmdc( ){
    long c,r;
    do{
      c=n;
      r=m%n;
      m=n;
      n=r;
    }
    while(r!=0);
    return c;
  }
  */
  interface CmmdcService {
      long cmmdc(long m, long n);
  }

  static CmmdcService cmmdcService=(long m, long n) -> 
    { 
      long r,c;
      do{
         c=n;
         r=m%n;
         m=n;
         n=r;
      }
      while(r!=0);
      return c;
    }; 

}