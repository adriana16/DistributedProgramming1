import java.util.logging.Level;
import java.util.logging.Logger; 

public class Exemplu{
  static Logger logger = Logger.getLogger(Exemplu.class.getName());
 
  public static void main(String args[]) {
    logger.log(Level.SEVERE,"SEVERE : Hello");
    //logger.severe("SEVERE : Hello");
    logger.log(Level.WARNING,"WARNING : Hello");
    //logger.warning("WARNING : Hello");
    logger.log(Level.INFO,"INFO : Hello");
    /*
    logger.log(Level.CONFIG,"CONFIG : Hello");
    logger.log(Level.FINE,"FINE : Hello");
    logger.fine("FINE : Hello");
    logger.log(Level.FINER,"FINER : Hello");
    logger.log(Level.FINEST,"FINEST : Hello");
    */
  }
}