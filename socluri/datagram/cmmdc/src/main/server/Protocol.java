package server;
import java.io.Serializable;

public class Protocol implements Serializable{
  public long x,y;
  public Protocol(long x,long y){
    this.x=x;
    this.y=y;
  }
}