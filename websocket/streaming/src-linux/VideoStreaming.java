import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;  
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

@ServerEndpoint(value="/video")
public class VideoStreaming{
  private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
  
  @OnMessage
  public void myTask(String msg,Session session) 
      throws IOException,EncodeException{
    String fs=System.getProperty("file.separator");
    
    String path0=fs+"VideoStreaming"+fs+"WEB-INF"+fs+"videos"+fs;
    // apache-tomcat 
    String pathServer="/home/mk/JavaApp/apache-tomcat-8.5.6";
    String path=pathServer+fs+"webapps"+path0;
     
    // glassfish
    /*
    String pathServer="/home/mk/JavaApp/glassfish4";
    String pathGlassfish=pathServer+fs+"glassfish"+fs+"domains"+fs+"domain1"
    String path=pathGlassfish+fs+"applications"+path0;
    */
    int noFile=Integer.parseInt(msg);
    String fileName="";
    switch(noFile){
    case 1:
      fileName="airhorse.avi"; 	  
      break;
    case 2:  
      fileName="video1.avi"; 	  
      break;	
    }    
    Path cale=Paths.get(path+fileName);
    for (Session peer : sessions) {
      if(peer.equals(session)){
        OutputStream out=peer.getBasicRemote().getSendStream();			
		    Files.copy(cale,out);        
        out.close();
      }
    }              
  }
  
  @OnOpen
  public void onOpen(Session session){
    sessions.add(session);
  }
     
  @OnClose
  public void onClose(Session session){
    sessions.remove(session);
  }
}
