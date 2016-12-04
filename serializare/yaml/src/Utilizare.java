import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
//import org.yaml.snakeyaml.TypeDescription;
import java.io.PrintWriter;
import org.yaml.snakeyaml.constructor.Constructor;
//import org.yaml.snakeyaml.constructor.SafeConstructor;
import java.util.Collection;
import java.util.Iterator;

public class Utilizare{
  //@SuppressWarnings("unchecked")
  public static void main(String[] args) {
    Constructor constructor=new Constructor(Disciplina.class);
    //TypeDescription disciplinaDescription=new TypeDescription(Disciplina.class);
    //disciplinaDescription.putListPropertyType("nume",String.class);
    //disciplinaDescription.setTag("nume");
    //constructor.addTypeDescription(disciplinaDescription);
   
    try{
      InputStream input = new FileInputStream("file.yaml");
      Yaml yaml = new Yaml();
      Object data = yaml.load(input);
      System.out.println("Continutul incarcat:\n");
      System.out.println(data.toString());
      
      Map<String,String> map = (Map)data;
      Collection<String> discipline=map.values();
      Iterator<String> iter=discipline.iterator();
      Yaml yaml1 = new Yaml(constructor);
      System.out.println("Regasirea datelor:\n");
      while(iter.hasNext()){
        String sobj=iter.next();
        // Abordare urata !!
        sobj=sobj.substring(14,sobj.length()-2);
        //System.out.println(sobj);              
        Disciplina obj=(Disciplina)yaml1.load(sobj);      
        System.out.println(obj.getNume());
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}  
