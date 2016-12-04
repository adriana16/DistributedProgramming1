package entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity
public class Adrese implements Serializable{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String nume;
  private String email;
  /*
  public Adrese(){}
  
  public Adrese(int id, String nume, String email) {
    this.id = id;
    this.nume = nume;
    this.email = email;
  }
  */
  public String getNume() {
    return nume;
  }
  public void setNume(String nume) {
    this.nume = nume;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
}
