public class Cmmdc implements java.io.Serializable{
  //private static final long serialVersionUID = 1L;
  
  public long cmmdc(long a, long b) {
    while (b != 0) {
      long r = a % b;
      a = b;
      b = r;
    }
    return a;
  }
}
