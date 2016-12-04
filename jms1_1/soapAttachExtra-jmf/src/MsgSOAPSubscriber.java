import javax.jms.TopicConnection;
import javax.jms.Session;
import javax.jms.TopicSession;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Message;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import javax.jms.TextMessage;
import com.sun.messaging.xml.MessageTransformer;
import java.util.Iterator;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.AttachmentPart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.FileReader;
import com.sun.messaging.xml.MessageTransformer;
import java.awt.Image;
//import net.sf.fmj.utility.URLUtils;
//import net.sf.fmj.ui.FmjStudio;
import java.net.URL;
import java.net.MalformedURLException;
import javax.swing.JFrame;

public class  MsgSOAPSubscriber extends Thread{

  public void run(){
    try{
      com.sun.messaging.TopicConnectionFactory cf=
        new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","jonathan");
      //cf.setProperty("imqBrokerHostPort","7676");
      TopicConnection conn=cf.createTopicConnection();
      TopicSession session=
        conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
      //Topic t=new com.sun.messaging.Topic("Date");
      Destination t=session.createTopic("Date");
      TopicSubscriber consumer=session.createSubscriber((Topic)t);
      
      conn.start();
      Message msg=null;
      MessageFactory mf=MessageFactory.newInstance();
          while((msg=consumer.receive())!=null){
        SOAPMessage soapMsg=
          MessageTransformer.SOAPMessageFromJMSMessage(msg,mf);
        System.out.println("Mesaj SOAP receptionat");
        // Now extract the attachments
        Iterator iterator = soapMsg.getAttachments();
        while (iterator.hasNext()) {
          AttachmentPart attached=(AttachmentPart)iterator.next();
          String id = attached.getContentId();
          String type = attached.getContentType();
          System.out.println("Attachment " + id + " has content type " + type);
          if(type.equals("text/plain")) {
            String content = (String)attached.getContent();
            System.out.println("Attachment contains:\n" + content);
          }
          if(type.equals("image/jpeg")){
            Image image=(Image)attached.getContent();
            //System.out.println("OK image");
            ShowImage s=new ShowImage(image);
            s.show();
          }   
          if(type.equals("audio/x-wav")){
            //System.out.println("Type : "+type);
            System.out.println("Play MP3");   
            MP3Player mp3Player=new MP3Player(attached.getRawContent());    
            mp3Player.start();  
          }
          if(id.startsWith("attached_video")){
          //if(type.equals("application/x-troff-msvideo")){  
            String videoFileName = "videoFile."+id.substring(14);
            //System.out.println("videoFileName = "+videoFileName);
            InputStream inputStream = attached.getRawContent();
            File f=new File(videoFileName);
            OutputStream out=new FileOutputStream(f);
            byte buf[]=new byte[1024];
            int len;
            while((len=inputStream.read(buf))>0)out.write(buf,0,len);
            out.close();
            
            String absolutePath=f.getAbsolutePath();
            System.out.println(absolutePath);
        
            URL mediaURL=null;  
            try {
              mediaURL = new URL("file://"+absolutePath);				
              System.out.println(mediaURL);
            } 
            catch (MalformedURLException e) {
              System.out.println(e);
            }
            
            JFrame mediaTest = new JFrame("Movie Player");
            mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MediaPlayer mediaPanel = new MediaPlayer(mediaURL);
            mediaTest.add(mediaPanel);
            mediaTest.setSize(800, 700); // set the size of the player
            mediaTest.setLocationRelativeTo(null);
            mediaTest.setVisible(true);	
   
            /*
            String[]arguments = new String[1];
            arguments[0]=URLUtils.createUrlStr(f);
            System.out.println("Press Enter to continue !");
            System.in.read();
            System.out.println("Video show");
            FmjStudio.main(arguments);
            */
					}
        }
      }
      conn.close();
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }
    System.out.println("Subscriber finished");
  }
}
