import
 javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader; 
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.JsonString;
import javax.json.JsonNumber;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class ReadJSON{
  public static void main(String[] args){
    String fileName="exemplu.json";
    JsonArray array=null;
    String fs=System.getProperty("file.separator");
    try{
      String path = new java.io.File( "." ).getCanonicalPath();
      JsonReader jsonReader = Json.createReader(new FileReader(path+fs+fileName));
      array = jsonReader.readArray();
      jsonReader.close();
    }
    catch(IOException e){
      System.out.println("Ex : "+e.getMessage());
    }
    analyse(array);
  }
  
  private static void analyse(JsonArray v){
    Iterator<JsonValue> iterator=v.iterator();
    while(iterator.hasNext()){
      JsonValue value=(JsonValue)iterator.next();
      if(value instanceof JsonArray){
        JsonArray array=(JsonArray)value;
        analyse(array);
      }
      if(value instanceof JsonObject){
        JsonObject obj=(JsonObject)value;
        analyseJsonObject(obj);
      }      
      if(value instanceof JsonString){
        JsonString string=(JsonString)value;
        String s=string.getString();
        System.out.println(s);
      }  
      if(value instanceof JsonNumber){
        JsonNumber number=(JsonNumber)value;
        double d=number.doubleValue();
        System.out.println(d);
      }  
    } 
  }
   
  private static void analyseJsonObject(JsonObject obj){
    Map<String,JsonValue> object=(Map)obj;
    Set<String> keys=object.keySet();
    Iterator<String> iter=keys.iterator();
    while(iter.hasNext()){
      String name=iter.next();
      System.out.println();
      System.out.println("JsonObject name : "+name);
      JsonValue vv=(JsonValue)object.get(name);
      if(vv instanceof JsonArray){
        JsonArray array=(JsonArray)vv;
        analyse(array);
      }
      if(vv instanceof JsonObject){
        JsonObject o=(JsonObject)vv;
        analyseJsonObject(o);
      } 
      if(vv instanceof JsonString){
        JsonString string=(JsonString)vv;
        String s=string.getString();
        System.out.println(s);
      }  
      if(vv instanceof JsonNumber){
        JsonNumber number=(JsonNumber)vv;
        double d=number.doubleValue();
        System.out.println(d);
      }        
    }
  } 
}  
