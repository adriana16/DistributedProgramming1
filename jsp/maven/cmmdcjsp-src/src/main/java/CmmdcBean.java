package cmmdc;
public class CmmdcBean{  
  private String m="";
  private String n="";
  private String cmmdc;
  
  public void setM(String m){
     this.m=m;
  }
  public void setN(String n){
     this.n=n;
  }
  public String getM(){
     return m;
  }
  public String getN(){
     return n;
  }
  
  public String getCmmdc(){
    long a=Long.parseLong(m);
    long b=Long.parseLong(n);
    return (new Long(cmmdc(a,b))).toString();
  }
  
  long cmmdc(long m,long n){
    long r,c;
    do{
      c=n;
      r=m%n;
      m=n;
      n=r;
    }
    while(r!=0);
    return c;
  }
}
