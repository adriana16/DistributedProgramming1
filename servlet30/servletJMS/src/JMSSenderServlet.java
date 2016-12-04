package jms;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSConsumer;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/sender") 

public class JMSSenderServlet extends HttpServlet{
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException {
    res.setContentType("text/html");
    ServletOutputStream out = res.getOutputStream();
    String m=req.getParameter("m");
    String n=req.getParameter("n");
    String topic=req.getParameter("topic");
    String clientID=req.getParameter("clientID");
    String clientName=req.getParameter("clientName");
    String msg=m+" "+n+" "+topic;
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic("Cmmdc"); 
      JMSContext ctx=cf.createContext();
      
      Topic t1=new com.sun.messaging.Topic(topic); 
      ctx.setClientID(clientID);
      JMSConsumer consumer = ctx.createDurableConsumer(t1,clientName);
     
      JMSProducer producer=ctx.createProducer();     
      producer.send(t,msg);
      
      ctx.close();
      out.println("<html><body bgcolor=\"#ccbbcc\"><center>");
      out.println("<h1> JSP Cmmdc </h1>");
      out.println("<p>");
      out.println("Datele au fost expediate serverului");
      out.println("</center></body></html>");
    }
    catch(Exception e){
      out.println(e.getMessage());
    }
    out.close();
    System.out.println("Publisher finished");
  }
  
  public void doPost(HttpServletRequest  req,HttpServletResponse res)
      throws ServletException,IOException {
    doGet(req,res);
  }
}         

    