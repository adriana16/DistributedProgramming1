package jms;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.jms.TextMessage;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 

@WebServlet(urlPatterns = "/receiver") 

public class JMSReceiverServlet extends HttpServlet{
  
  public void doGet(HttpServletRequest  req,HttpServletResponse res)
      throws ServletException,IOException {
    res.setContentType("text/html");
    ServletOutputStream out = res.getOutputStream();
    String topic=req.getParameter("topic");
    String clientID=req.getParameter("clientID");
    String clientName=req.getParameter("clientName");
    //String clientID="JMSCmmdc";
    //String clientName="JMSCmmdc";
    try{
      // Varianta Oracle-Sun Message Topic
      com.sun.messaging.TopicConnectionFactory cf
        = new com.sun.messaging.TopicConnectionFactory();
      //cf.setProperty("imqBrokerHostName","host");
      //cf.setProperty("imqBrokerHostPort","7676");
      Topic t=new com.sun.messaging.Topic(topic); 
      JMSContext ctx=cf.createContext();
      ctx.setClientID(clientID);
      JMSConsumer consumer = ctx.createDurableConsumer(t,clientName);
      TextMessage txtMsg=(TextMessage)consumer.receive();
      String cmmdc=txtMsg.getText();
      ctx.close();
      out.println("<html><body bgcolor=\"#ccbbcc\"><center>");
      out.println("<h1> JSP Cmmdc </h1>");
      out.println("<p>");
      out.println("Rezultatul obtinut : "+cmmdc);
      out.println("</center></body></html>");
    }
    catch(Exception e){
      out.println(e.getMessage());
    }
    out.close();
    System.out.println("Subscriber finished");
  }
  
  public void doPost(HttpServletRequest  req,HttpServletResponse res)
      throws ServletException,IOException {
    doGet(req,res);
  }
}         

    