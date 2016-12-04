import java.util.Map;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.TypeDescription;
import java.io.PrintWriter;
//import org.yaml.snakeyaml.constructor.Constructor;
//import org.yaml.snakeyaml.constructor.SafeConstructor;

public class Generare{
  //@SuppressWarnings("unchecked")
  public static void main(String[] args) {
    //Constructor constructor=new Constructor(Disciplina.class);
    //TypeDescription disciplinaDescription=new TypeDescription(Disciplina.class);
    //disciplinaDescription.putListPropertyType("nume",String.class);
    //disciplinaDescription.setTag("nume");
    //constructor.addTypeDescription(disciplinaDescription);
    
    Yaml yaml = new Yaml();//(new SafeConstructor());
    Map<String,String> data = new HashMap<String,String>();
    
    Disciplina an=new Disciplina("Analiza numerica");
    String san=yaml.dump(an);
    data.put("an",san);
    //System.out.println(san);
    
    Disciplina pd=new Disciplina("Programare distribuita");
    String spd=yaml.dump(pd);
    data.put("pd",spd);
    //System.out.println(spd);
    
    Disciplina sm=new Disciplina("Soft matematic");
    String ssm=yaml.dump(sm);
    data.put("sm",ssm);
    //System.out.println(ssm);
    
    System.out.println("Serializarea datelor");
    System.out.println("Continutul obtinut:\n");
    try{
      PrintWriter pw=new PrintWriter("file.yaml");
      String objYAML=yaml.dump(data);
      System.out.println(objYAML);
      pw.write(objYAML);
      pw.flush();
    }
    catch(Exception e){
      e.printStackTrace();
    } 
  }
}  

