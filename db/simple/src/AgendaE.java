import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AgendaE{
  private static String jdbcURIDerby = "jdbc:derby://localhost:1527/AgendaEMail";
  private static String jdbcDriverDerby = "org.apache.derby.jdbc.ClientDriver";
  
  private static String jdbcURIMysql="jdbc:mysql://localhost:3306/AgendaEMail?user=root";
  private static String jdbcDriverMysql="com.mysql.jdbc.Driver";
  
  private static String jdbcDriverPostgresql="org.postgresql.Driver";
  private static String jdbcURIPostgresql="jdbc:postgresql://localhost:5432/AgendaEMail";
  
  private static String jdbcDriverHsqldb="org.hsqldb.jdbcDriver";
  private static String jdbcURIHsqldb="jdbc:hsqldb:hsql://localhost/AgendaEMail";
  
  private static String jdbcDriverOracle="oracle.jdbc.driver.OracleDriver";
  private static String jdbcURIOracle="jdbc:oracle:thin:@localhost:1521:XE";
  
  private static String jdbcDriverH2="org.h2.Driver";
  private static String jdbcURIH2="jdbc:h2:tcp://localhost/agendaemail";
  
  public static void main(String[] args){
    String dbms=null,username=null,password=null,jdbcURI=null;
    System.out.println(args.length);
    switch(args.length){
      case 0:
        System.out.println("At least one argument required");
        System.out.println("DBMS username password");
        System.out.println("DBMS derby,mysql,postgresql,hsqldb,oracle,H2");
        System.exit(0);
        break;
      case 1:  
        dbms=args[0];
        break;
      case 2:
        dbms=args[0];
        username=args[1];
        password="";
        break;
      default:
        dbms=args[0];
        username=args[1];
        password=args[2];        
    }
    Connection conn = null;
    try {
      switch(dbms){
        case "derby":
          Class.forName(jdbcDriverDerby).newInstance();
          jdbcURI=jdbcURIDerby;
          break;
        case "mysql":
          Class.forName(jdbcDriverMysql).newInstance();
          jdbcURI=jdbcURIMysql;
          break;
        case "hsqldb":
          Class.forName(jdbcDriverHsqldb).newInstance();
          jdbcURI=jdbcURIHsqldb;
          break;
        case "oracle":
          Class.forName(jdbcDriverOracle).newInstance();;
          jdbcURI=jdbcURIOracle;          
          break;
        case "H2":
          Class.forName(jdbcDriverH2).newInstance();;
          jdbcURI=jdbcURIH2;          
          break;  
        default:
          System.out.println("Unknown DBMS...");
          System.exit(0);
      }  
      System.out.println("jdbcURI="+jdbcURI);
      if(password==null)       
        conn = DriverManager.getConnection(jdbcURI);
      else
        conn=DriverManager.getConnection(jdbcURI,username,password);

      Statement instructiune=conn.createStatement();
      Scanner scanner=new Scanner(System.in);
      int prel,no;
      String ch="Y",nume="",email="",sql="";
      ResultSet rs=null;
     
      while(ch.startsWith("Y")){
        do{
          System.out.println("Continue ? (Y/N)");
          ch=scanner.next().toUpperCase();
        }
        while((!ch.startsWith("Y"))&&(!ch.startsWith("N")));
        if(ch.startsWith("Y")){
          System.out.println("Natura interogarii ?");
          System.out.println("(Dupa nume:1,Dupa email:2)");
          do{
            prel=0;
            try{
              prel=scanner.nextInt();
            }
            catch(InputMismatchException e){}
          }
          while((prel<1)&&(prel>2));
          switch(prel){
            case 1 :
              System.out.println("Numele");              
              nume='\''+scanner.next().trim()+'\'';
              sql="select * from adrese where nume="+nume;
              rs=instructiune.executeQuery(sql);        
              break;
            case 2 :
              System.out.println("Email");
              email='\''+scanner.next().trim()+'\'';
              sql="select * from adrese where email="+email;
              rs=instructiune.executeQuery(sql);
              break;
            default: System.out.println("Comanda eronata");
          }
          if(rs!=null){
            System.out.println("Results :");
            while(rs.next()){
              System.out.println("id=" + rs.getInt("id"));
              System.out.println("nume=" + rs.getString("nume"));
              System.out.println("email=" + rs.getString("email"));
              System.out.println("-----------------");
            }
          }
          else{
            System.out.println("No item found !");
          }           
        }
      }
    }
    catch(Exception e) {
      // handle the exception
      e.printStackTrace();
    } 
  } 
}
