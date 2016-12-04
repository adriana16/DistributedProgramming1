import CmmdcApp.*;
import org.omg.CORBA.ORB;

public class CmmdcImpl extends CmmdcPOA {
  private ORB orb;

  public CmmdcImpl(ORB orb) {
    this.orb = orb; 
  }
  
  public long cmmdc(long a,long b){
    if (a==b)
      return a;
    else
      if (a<b)
        return cmmdc(a,b-a);
      else
        return cmmdc(a-b,b);
  }
}