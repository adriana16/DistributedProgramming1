import java.io.IOException;
import java.net.URI;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import javax.websocket.Session;
import java.util.Scanner;

import javax.websocket.Endpoint; 
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.awt.Image;

public class WebSocketClient extends Endpoint{
  private static boolean sfarsit=false;
  private static String SERVER = "ws://localhost:8080/ImageStreaming/image";
  
  public static void main(String[] args){
    Scanner scanner=new Scanner(System.in);
    System.out.println("Fisierul de descarcat:");
    System.out.println("1: Imagine din Brasov");
    System.out.println("2: pic-xml");
    int noFile=scanner.nextInt();
    String data=new Integer(noFile).toString();
    WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    
    try {
      Session session=container.connectToServer(WebSocketClient.class, null, new URI(SERVER));
      session.getBasicRemote().sendText(data); 
      while(! sfarsit){;};
    } 
    catch(Exception ex){
       System.out.println("LocalEndPoint Exception : "+ex.getMessage());
    } 
  }
  
  public void onOpen(Session session, EndpointConfig config){
    session.addMessageHandler(new MessageHandler.Whole<byte[]>() {
       public void onMessage(byte[] msg){
         //System.out.println(msg.length);
         try{
           InputStream in=new ByteArrayInputStream(msg);
           BufferedImage bi=ImageIO.read(in);
           Image image=(Image)bi;
           ShowImage s=new ShowImage(image);
           s.show();
         }
         catch(IOException e){
           e.printStackTrace();
         }         
         sfarsit=true;
       }  
    });
  } 
}
