package timer;
import java.util.Timer;
import java.util.TimerTask;
import java.io.PrintWriter;
import java.util.Calendar;

public class MyTimer{
  private Timer timer;
  private PrintWriter out;
  private StringBuffer sb;
  //private Calendar calendar; 
  
  public MyTimer(int seconds, PrintWriter out,StringBuffer sb){
    this.out=out;
    this.sb=sb;
    //sb=new StringBuffer(1000);
    timer=new Timer();
    long ms=1000;
    timer.scheduleAtFixedRate(new MyTask(),0*ms,seconds*ms);
    Calendar calendar=Calendar.getInstance();
    String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+
                ":"+calendar.get(Calendar.SECOND);
    System.out.println("Timer is started : "+time); 
    sb.append("<br/>");    
    sb.append("Timer is started : "+time);  
    out.println("<html>"); 
    out.println("<body>");
    out.println("<h1>TimerServlet</h1>");
    out.println("<p/>");
    out.println(sb.toString()); 
    out.println("<p/>");
    out.println( "<a href=\"index.html\">Start Page</a>");
    out.println("</body>");
    out.println("</html>");     
  }
  
  public void timerStopped(){
    timer.cancel();
    Calendar calendar=Calendar.getInstance();
    String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+
                ":"+calendar.get(Calendar.SECOND);
    System.out.println("Timer is stopped : "+time);    
    sb.append("<br/>");
    sb.append("Timer is stopped : "+time); 
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>TimerServlet</h1>");
    out.println("<p/>");    
    out.println(sb.toString());
    out.println("<p/>");
    out.println( "<a href=\"index.html\">Start Page</a>");
    out.println("</body>");
    out.println("</html>");       
  }
  
  public String getSb(){
    return sb.toString();
  }
  
  class MyTask extends TimerTask {
    @Override
    public void run() {
      Calendar calendar=Calendar.getInstance();
      String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+
                ":"+calendar.get(Calendar.SECOND);
      System.out.println("Hello world : "+time);
      sb.append("<br/>");
      sb.append("Current time : "+time);  
      //out.println(sb.toString());  
      /*
      out.println("<html>");
      out.println("<body>");
      out.println("<h1>TimerServlet</h1>");
      out.println("<p/>");    
      out.println(sb.toString());
      out.println("<p/>");
      out.println( "<a href=\"index.html\">Start Page</a>");
      out.println("</body>");
      out.println("</html>");    
      */      
    }
  }
}


