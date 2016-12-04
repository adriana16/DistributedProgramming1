package numara.ejb;
import javax.ejb.Remote;

@Remote
public interface Numara{
  public int getIndex();
  public void remove();
}  