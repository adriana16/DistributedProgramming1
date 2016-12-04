package logtest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exemplu{
  static Logger logger=LoggerFactory.getLogger("Exemplu");
  public static void main(String args[]) {
    //logger.setLevel(Level.ALL);
    logger.trace("TRACE : Hello"); 
    logger.debug("DEBUG : Hello");
    logger.info("INFO : Hello");
    logger.warn("WARN : Hello");
    logger.error("ERROR : Hello");
  }
}