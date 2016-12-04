import java.util.Scanner;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import java.io.File;

public class ClientDownloadServlet{
  static String uri="http://localhost:8080/download/download";
	
  public static void main(String[] args) {  
    Scanner scanner=new Scanner(System.in);
    System.out.println("Alege fisierul :");
    System.out.println("1 : capitol.txt");
    System.out.println("2 : TomJones.mp3");
    System.out.println("3 : clock.avi");
    System.out.println("4 : xml-pic.jpg");
    
    int n=scanner.nextInt();
    String fileName="";
    switch (n) {
      case 1:
        fileName="capitol.txt";
        break;
      case 2:
        fileName="TomJones.mp3";
        break;
      case 3:
        fileName="clock.avi";
        break;
      case 4:
        fileName="xml-pic.jpg";
        break; 
      default:
        System.out.println("Alegere eronata");
        System.exit(0);      
    }

    System.out.println("Post method request");
    try{
      Request.Post(uri)
        .bodyForm(Form.form()
           .add("file",fileName)
           .build())
        .execute().saveContent(new File(fileName));
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    } 
  }
}
