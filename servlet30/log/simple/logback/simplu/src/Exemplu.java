import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.Logger;

public class Exemplu{
  static ch.qos.logback.classic.Logger logger = 
    (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("Exemplu");
 
  public static void main(String args[]) {
    logger.setLevel(Level.ALL);
    logger.trace("TRACE : Hello");
    
    logger.debug("DEBUG : Hello");
    
    logger.info("INFO : Hello");
    logger.warn("WARN : Hello");
    logger.error("ERROR : Hello");
  }
}