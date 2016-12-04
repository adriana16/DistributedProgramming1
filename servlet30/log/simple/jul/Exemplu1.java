import java.util.logging.Level;
import java.util.logging.Logger; 
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class Exemplu1{
  static Logger logger = Logger.getLogger(Exemplu1.class.getName());
 
  public static void main(String args[]) {
    try{
      FileHandler loggingFile = new FileHandler("Logging.txt");
      loggingFile.setFormatter(new SimpleFormatter());
      logger.addHandler(loggingFile);
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
    //logger.log(Level.SEVERE,"SEVERE : Hello");
    //logger.log(Level.WARNING,"WARNING : Hello");
    //logger.log(Level.INFO,"INFO : Hello");
    logger.severe("SEVERE : Hello");
    logger.warning("WARNING : Hello");
    logger.info("INFO : Hello");
  }
}