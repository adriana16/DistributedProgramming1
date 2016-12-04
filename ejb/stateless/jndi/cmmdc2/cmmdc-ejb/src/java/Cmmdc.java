package cmmdc.ejb;
import javax.ejb.Remote;

@Remote
public interface Cmmdc{
  public long cmmdc(long m,long n);
}