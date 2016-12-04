package numara.ejb;
import javax.ejb.Stateful;
import javax.ejb.Remove;

@Stateful
public class NumaraBean implements Numara{
  private int index=0;
  public int getIndex(){
     return ++index;
  }   
  
  @Remove
  public void remove() {}
}