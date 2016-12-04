import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter; 
import javax.json.Json;
import java.io.PrintWriter;
import java.io.IOException;

public class GenerateJSON{
  public static void main(String[] args){
    JsonArray jsonArray=Json.createArrayBuilder()
       .add(Json.createObjectBuilder()
          .add("nume","Analiza numerica"))
       .add(Json.createObjectBuilder()
         .add("nume","Programare distribuita"))
       .add(Json.createObjectBuilder()
         .add("nume","Soft matematic"))
       .add(100)
       .add("javax.json")   
       .add(Json.createArrayBuilder()
         .add(1)
         .add(2)
         .add(3))  
       .add(Json.createArrayBuilder()
         .add(4)
         .add(5)
         .add(6))              
      .build();
    System.out.println("System.out : "+jsonArray);
    String fileName="exemplu.json";
  
    try{
      JsonWriter jsonWriter=Json.createWriter(new PrintWriter(fileName));
      jsonWriter.writeArray(jsonArray);
      jsonWriter.close();   
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }    
    JsonWriter jsonWriter=Json.createWriter(new PrintWriter(System.out));
    jsonWriter.writeArray(jsonArray);
    jsonWriter.close();
  }  
}  