package ejb;
import javax.ejb.Stateless; 
import javax.persistence.PersistenceContext; 
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entity.Adrese;
import java.util.List;

@Stateless//(name="ejb/agendae") 
public class AgendaEMailBean implements AgendaEMail{
  @PersistenceContext(unitName="agendae_persistence_ctx")
  EntityManager em;
  
  public List<Adrese> getEmail(String nume){
    String nm="\'"+nume+"\'";
    String sql="SELECT entity FROM Adrese entity WHERE entity.nume="+nm;
    System.out.println(sql);
    Query query=em.createQuery(sql);
    List<Adrese> list=(List<Adrese>)query.getResultList();
    return list;
  }
  
  public List<Adrese> getNume(String email){
    String eml="\'"+email+"\'";
    String sql="SELECT entity FROM Adrese entity WHERE entity.email="+eml;
    System.out.println(sql);
    Query query=em.createQuery(sql);
    List<Adrese> list=(List<Adrese>)query.getResultList();
    return list;
  }
  
  /*
  public void createCustomers() {
    Customer c1 = new Customer();
    c1.setId(1);
    c1.setName("XYZ");
    em.persist(c1); 	
  }
  */
}