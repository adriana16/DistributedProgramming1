import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
    
public class Exemplu{
  static Logger logger = LogManager.getLogger(Exemplu.class);

  public static void main(String args[]) {
    logger.warn("WARN : Hello");
    logger.debug("DEBUG : Hello");
    logger.info("INFO : Hello");
    logger.fatal("FATAL : Hello");
  }
}