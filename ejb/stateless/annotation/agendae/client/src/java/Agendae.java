package agendae.ejb;
import javax.ejb.Remote;

@Remote
public interface Agendae{
  public String getResult(String criteriu,String val);
}