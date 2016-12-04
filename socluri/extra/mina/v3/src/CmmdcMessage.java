public class CmmdcMessage{
  private long m,n;
  private long result;

  public long getM() {
    return m;
  }
  public void setM(long m) {
    this.m = m;
  }

  public long getN() {
    return n;
  }
  public void setN(long n) {
    this.n = n;
  }

  public long getResult() {
    return result;
  }
  public void setResult(long result) {
    this.result=result;
  }

  public String toString(){
    return "CmmdcMessage("+m+","+n+")";
  }
}
