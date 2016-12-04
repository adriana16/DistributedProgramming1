package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientFXCmmdc extends Application {

  public static void main(String[] args) {
    Application.launch(ClientFXCmmdc.class, args);
  }
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Client JavaFX Cmmdc");
    Group root = new Group();
    Scene scene = new Scene(root, 600, 250, Color.LIGHTGREEN);
    
    GridPane gridpane=new GridPane();
    
    Label labelM=new Label("Primul numar");
    GridPane.setConstraints(labelM, 1, 1); 
    Label labelN=new Label("Al doilea numar");
    GridPane.setConstraints(labelN, 1, 2); 
    Label labelServer=new Label("Server");
    GridPane.setConstraints(labelServer, 1, 3); 
    Label labelPort=new Label("Port");
    GridPane.setConstraints(labelPort, 1, 4); 
    Label labelRez=new Label("Rezultat");
    GridPane.setConstraints(labelRez, 1, 5); 
    
    final TextField TextFieldM=new TextField("1");
    GridPane.setConstraints(TextFieldM, 2, 1); 
    final TextField TextFieldN=new TextField("1");
    GridPane.setConstraints(TextFieldN, 2, 2);
    final TextField TextFieldServer=new TextField("localhost");
    GridPane.setConstraints(TextFieldServer, 2, 3);
    final TextField TextFieldPort=new TextField("7999");
    GridPane.setConstraints(TextFieldPort, 2, 4);
    final TextField TextFieldRez=new TextField();
    GridPane.setConstraints(TextFieldRez, 2, 5);
    TextFieldRez.setVisible(false);
    
    Button btn=new Button("Calculeaza");
    GridPane.setConstraints(btn, 2, 6);
    btn.setOnAction((new EventHandler<ActionEvent>() {
        public void handle(ActionEvent me) {
           String sm=TextFieldM.getText();
           String sn=TextFieldN.getText();
           String server=TextFieldServer.getText();
           String sport=TextFieldPort.getText();
           long m=Long.parseLong(sm);
           long n=Long.parseLong(sn);
           int port=Integer.parseInt(sport);
           try(Socket cmmdcSocket = new Socket(server, port); 
              DataInputStream in=new DataInputStream(cmmdcSocket.getInputStream());
              DataOutputStream out=new DataOutputStream(cmmdcSocket.getOutputStream())){
             out.writeLong(m);
             out.writeLong(n);
             long r=in.readLong();
             TextFieldRez.setText((new Long(r)).toString());
           } 
           catch(Exception e){
             TextFieldRez.setText("Comunication error : "+e.getMessage());
           }
        
           TextFieldRez.setVisible(true);          
        } 
    }));   
    gridpane.setVgap(8);
    gridpane.setHgap(8);
    gridpane.getChildren().addAll(labelM,labelN,labelServer,labelPort,labelRez);
    gridpane.getChildren().addAll(TextFieldM,TextFieldN,TextFieldServer,TextFieldPort,TextFieldRez);
    gridpane.getChildren().addAll(btn);
    root.getChildren().add(gridpane);  
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
