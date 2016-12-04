import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; 
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/adrese")
public class AgendaEMailServlet extends HttpServlet {
  Statement instructiune=null;
  Connection con=null;

  public void init(ServletConfig config) throws ServletException{
    super.init(config);
    // SGBD Derby
    String jdbcDriver="org.apache.derby.jdbc.ClientDriver";
    String URLBazaDate="jdbc:derby://localhost:1527/AgendaEMail";
    // SGBD Mysql
    //String jdbcDriver="com.mysql.jdbc.Driver";
    //String URLBazaDate="jdbc:mysql://localhost:3306/AgendaEMail?user=root";

    try{
      Class.forName(jdbcDriver).newInstance();
      con=DriverManager.getConnection(URLBazaDate);
      instructiune=con.createStatement();
    }
    catch(ClassNotFoundException e){
      System.out.println("Driver inexistent JDBC: "+jdbcDriver);
    }
    catch(SQLException e){
      System.out.println("Baza de date inexistenta "+URLBazaDate);
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
    }    
  }   

  public void destroy(){
    try{
      if(con!=null) con.close();
    }
    catch(SQLException e){
      System.out.println(e.getMessage());
    }      
  }

  public void doGet(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
    String myAtribut,myVal;
    res.setContentType("text/html");
    ServletOutputStream out = res.getOutputStream();
   
    myAtribut=req.getParameter("criteriu");
    myVal=req.getParameter("termen");
    myVal='\''+myVal+'\'';
    try{
      String sql="select * from adrese where "+ myAtribut+" = "+myVal;
      ResultSet rs=instructiune.executeQuery(sql);
      out.println("<html>");
      out.println("<head><title>AgendaEMail</title></head>");
      out.println("<body>");
      out.println("<h1>Agenda de Adrese e-mail </h1>");
      out.println("<p/>"); 
      out.println("<b>    Nume    <-->    Adresa e-mail  </b>");
      out.println("<br/>");
      while(rs.next()){
        out.print(rs.getString("nume")+" <--> "+rs.getString("email"));
        out.println("<br/>");
      }
      out.println("</body>");
      out.println("</html>");
      out.close();
    }
    catch(SQLException e){
      System.out.println("SQLException: "+e.getMessage());
    }
    catch(Exception e){
      System.out.println("Eroare : "+e.getMessage());
    }    
  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  }
}
