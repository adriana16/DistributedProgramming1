package cmmdc.ejb;
import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface Cmmdc{
  public Future<Long> cmmdc(long m,long n);
}