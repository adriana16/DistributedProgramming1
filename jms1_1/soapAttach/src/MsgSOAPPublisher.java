import javax.jms.TopicConnection;
import javax.jms.Session;
import javax.jms.TopicSession;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Message;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import javax.jms.TextMessage;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.Name;
import javax.xml.soap.AttachmentPart;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import com.sun.messaging.xml.MessageTransformer;
import javax.activation.DataHandler;
import java.net.URL;

public class  MsgSOAPPublisher extends Thread{

  String file_location, image_location;
  MsgSOAPPublisher(String file_location,String image_location){
  	this.file_location=file_location;
  	this.image_location=image_location;
  }
  
  public void run(){
    try{
      com.sun.messaging.TopicConnectionFactory cf=
  			new com.sun.messaging.TopicConnectionFactory();
  		//cf.setProperty("imqBrokerHostName","jonathan");
  		//cf.setProperty("imqBrokerHostPort","7676");
  		TopicConnection conn=cf.createTopicConnection();
  		TopicSession session=conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
  		//Topic t=new com.sun.messaging.Topic("Date");
  		//TopicPublisher publisher=session.createPublisher(t);
      Destination t=session.createTopic("Date");
      MessageProducer producer=session.createProducer(t);
  
      MessageFactory mf=MessageFactory.newInstance();
      SOAPMessage soapMsg=mf.createMessage();
      SOAPPart part=soapMsg.getSOAPPart();
  		SOAPEnvelope envelope=part.getEnvelope();
  		SOAPBody body=envelope.getBody();
  		Name bodyName=envelope.createName("verif");
      SOAPElement element=body.addBodyElement(bodyName);
      element.addTextNode("MyAttachment");
  
  	  // Crearea atasamentului de tip text
  
  		AttachmentPart attachment1=soapMsg.createAttachmentPart();
  
  		FileReader fr = new FileReader(new File(file_location+"/capitol.txt"));
  		BufferedReader br = new BufferedReader(fr);
  
  		String stringContent = "";
  		String line = br.readLine();
  		while (line != null) {
  			stringContent = stringContent.concat(line);
  			stringContent = stringContent.concat("\n");
  			line = br.readLine();
  		}
  
  		attachment1.setContent(stringContent, "text/plain");
  		attachment1.setContentId("attached_text");
  
  		soapMsg.addAttachmentPart(attachment1);
  		
  	// Crearea atasamentului de tip imagine
  		URL url = new URL("file://"+image_location+"/xml-pic.jpg"); 
  		DataHandler dataHandler = new DataHandler(url); 
  		AttachmentPart attachment2 = 
  			soapMsg.createAttachmentPart(dataHandler); 
  
  		attachment2.setContentId("attached_image"); 
  
  		soapMsg.addAttachmentPart(attachment2);	
  		Message m=MessageTransformer.SOAPMessageIntoJMSMessage(soapMsg,session);
  		//publisher.publish(m);
  		producer.send(m);
  		FileOutputStream f=new FileOutputStream("MySOAPMessage.xml");
  		soapMsg.writeTo(f);
  		conn.close();
  	}
  	catch(Exception e){
  		System.out.println("Exception : "+e.getMessage());
  	}
  	System.out.println("Publisher finised");
	}
}
