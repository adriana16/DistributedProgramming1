import SumApp.*;
import org.omg.CORBA.*;

public class SumImpl extends SumPOA {
  private ORB orb;

  public SumImpl(ORB orb) {
    this.orb = orb; 
  }
  
  public double compute(double[] s){
    double suma=0;
    for(int i=0;i<s.length;i++) suma+=s[i];
    return suma;
  }
}