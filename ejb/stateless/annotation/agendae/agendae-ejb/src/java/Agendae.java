package agendae.ejb;
import javax.ejb.Remote;

@Remote
public interface Agendae{
  //public String getEmail(String name);
  //public String getName(String email);
  public String getResult(String criteriu,String val);
}