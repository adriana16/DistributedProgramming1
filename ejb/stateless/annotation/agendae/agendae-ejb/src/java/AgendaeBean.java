package agendae.ejb;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

@Stateless
public class AgendaeBean implements Agendae{
  //private static Statement instructiune;
  private static final Logger logger=Logger.getLogger(AgendaeBean.class.getName()); 
  
  public String getResult(String criteriu,String val){
    String jdbcURIDerby = "jdbc:derby://localhost:2527/AgendaEMail";
    String jdbcDriverDerby = "org.apache.derby.jdbc.ClientDriver";
    StringBuffer sb=new StringBuffer();
    logger.info("myINFO: " + criteriu+" : "+val);
    try{
      Class.forName(jdbcDriverDerby).newInstance();
      Connection conn=DriverManager.getConnection(jdbcURIDerby);
      Statement instructiune=conn.createStatement();
      String sql="select * from adrese where "+criteriu+"="+'\''+val+'\'';
      ResultSet rs=instructiune.executeQuery(sql);   
      if(rs!=null){
        while(rs.next()){
          sb.append(rs.getString("nume").trim()+"<->"+rs.getString("email").trim());
          sb.append(" ");
        }
      }
    }  
    catch(Exception e){
      e.printStackTrace();
    }
    return sb.toString();
  }
  /*
  public String getEmail(String name){
    myStatement();
    //logger.info("myINFO1: " + name);
    StringBuffer sb=new StringBuffer();
    Statement instructiune;
    try{
      String sql="select * from adrese where nume="+'\''name+'\'';
      ResultSet rs=instructiune.executeQuery(sql);   
      if(rs!=null){
        while(rs.next()){
          sb.append(rs.getString("email").trim());
          sb.append(" ");
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    //logger.info("myINFO2: " + sb.toString());
    return sb.toString();
  }
  
  public String getName(String email){
    myStatement();
    //logger.info("myINFO1: " + name);
    StringBuffer sb=new StringBuffer();
    String sql="select * from adrese where email="+'\''+email+'\'';
    try{
      ResultSet rs=instructiune.executeQuery(sql);   
      if(rs!=null){
        while(rs.next()){
          sb.append(rs.getString("nume").trim());
          sb.append(" ");
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    //logger.info("myINFO2: " + sb.toString());
    return sb.toString();
  }
  
  private void myStatement(){
    String jdbcURIDerby = "jdbc:derby://localhost:2527/AgendaEMail";
    String jdbcDriverDerby = "org.apache.derby.jdbc.ClientDriver";
    try{
      Class.forName(jdbcDriverDerby).newInstance();
      Connection conn=DriverManager.getConnection(jdbcURIDerby);
      instructiune=conn.createStatement();
    }  
    catch(Exception e){
      e.printStackTrace();
    }
  }
  */
}