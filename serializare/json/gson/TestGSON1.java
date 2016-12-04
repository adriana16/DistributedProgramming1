import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

class Disciplina{
  private String nume;
  Disciplina(){}
  
  Disciplina(String nume){
    this.nume=nume;
  }
  public String getNume(){
    return nume;
  }
}
  
public class TestGSON1{
  public static void main(String[] args){
    Gson gson=new Gson();
    Disciplina an=new Disciplina("Analiza numerica");
    Disciplina pd=new Disciplina("Programare distribuita");
    Disciplina sm=new Disciplina("Soft matematic");
    Disciplina[] discipline={an,pd,sm};
    String json=gson.toJson(discipline);
    System.out.println(json);
    Type collectionType = new TypeToken<Collection<Disciplina>>(){}.getType();
    Collection<Disciplina> d = gson.fromJson(json,collectionType);
    Iterator<Disciplina> iter=d.iterator();
    while(iter.hasNext()){
      Disciplina dis=iter.next();
      System.out.println(dis.getNume());
    }
    List<Disciplina> lst=new ArrayList<Disciplina>(3);
    lst.add(an);
    lst.add(pd);
    lst.add(sm);
   
    json=gson.toJson(lst);
    System.out.println(json);
  }
}  