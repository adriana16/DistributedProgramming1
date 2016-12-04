package single.ejb;
import javax.ejb.Singleton;
 
@Singleton
public class SingleBean implements Single{
  private int index=0;
  public int getIndex(){
     return ++index;
  }   
}