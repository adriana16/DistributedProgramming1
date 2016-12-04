import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Wv extends Application {
  private Scene scene;
  
  public static void main(String[] args) {
    Application.launch(Wv.class, args);
  }
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Show Web Page");
    scene = new Scene(new Browser(), 600,500, Color.LIGHTGREEN);
    
    primaryStage.setScene(scene);
    primaryStage.show();
  } 
}

class Browser extends Region {
  final WebView webView = new WebView();
  final WebEngine webEngine = webView.getEngine(); 
    
  public Browser(){
    webEngine.load("http://www.unitbv.ro");
    getChildren().add(webView);
  }
}
