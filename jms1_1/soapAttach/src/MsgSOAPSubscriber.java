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
import java.io.FileReader;
import com.sun.messaging.xml.MessageTransformer;
import java.awt.Image;

public class  MsgSOAPSubscriber extends Thread{

public void run(){
	
	try{
		com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
		//cf.setProperty("imqBrokerHostName","jonathan");
		//cf.setProperty("imqBrokerHostPort","7676");
		TopicConnection conn=cf.createTopicConnection();
		TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
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
                AttachmentPart attached = 
                    (AttachmentPart)iterator.next();
                String id = attached.getContentId();
                String type = attached.getContentType();
                System.out.println("Attachment " + id + 
                    " has content type " + type);
                if (type.equals("text/plain")) {
                    String content = (String)attached.getContent();
                    System.out.println("Attachment contains:\n" + content);
                }
				if (type.equals("image/jpeg")){
					Image image=(Image)attached.getContent();
					System.out.println("OK image");
					ShowImage s=new ShowImage(image);
					s.show();
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
