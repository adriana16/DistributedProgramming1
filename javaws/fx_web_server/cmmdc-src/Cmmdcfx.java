//package cmmdc;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 
import javafx.scene.control.Label; 
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import java.text.DecimalFormat; 


public class Cmmdcfx extends Application{
 
  public static void main(String[] args) {
    Application.launch(Cmmdcfx.class,args);
  }
    
  @Override 
  public void start(Stage primaryStage) {
    primaryStage.setTitle("CMMDC");
    Group root = new Group();
    Scene scene = new Scene(root, 600, 250, Color.LIGHTGREEN);
    GridPane gridPane=new GridPane(); 
    
    Label mLabel = new Label("Primul numar");
    gridPane.add(mLabel, 1, 1); // column=1 row=1
    final TextField tm=new TextField();
    gridPane.add(tm,2,1);
    
    Label nLabel = new Label("Al doilea numar");
    gridPane.add(nLabel, 1, 2); // column=1 row=1
    final TextField tn=new TextField();
    gridPane.add(tn,2,2);
    
    final TextField rez = new TextField();
    gridPane.add(rez, 2, 3);
    
		Button button = new Button("Calculeaza");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String sm = tm.getText();
        String sn = tn.getText();
        long m=Long.parseLong(sm);
        long n=Long.parseLong(sn);
        long r=cmmdc(m,n);
				rez.setText((new Long(r)).toString());
			}
		});
		gridPane.add(button, 1, 3);
		
		
    gridPane.setVgap(8);
    gridPane.setHgap(8);  
    root.getChildren().add(gridPane);  
    primaryStage.setScene(scene);
    primaryStage.show(); 
  }  
  
  private long cmmdc(long m,long n){
    long r, c;
    do {
      c = n;
      r = m % n;
      m = n;
      n = r;
    }
    while (r != 0);
    return c;
  } 
}
