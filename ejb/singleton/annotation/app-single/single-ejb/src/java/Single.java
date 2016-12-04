package single.ejb;
import javax.ejb.Remote;

@Remote
public interface Single{
  public int getIndex();
}  