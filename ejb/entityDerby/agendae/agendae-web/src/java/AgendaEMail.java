package ejb;
import javax.ejb.Remote; 
import entity.Adrese;
import java.util.List;

@Remote 
public interface AgendaEMail{
  public List<Adrese> getEmail(String nume);
  public List<Adrese> getNume(String email);
}