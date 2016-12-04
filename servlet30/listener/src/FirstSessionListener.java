import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class FirstSessionListener implements HttpSessionListener {
  static int users = 0;

  public void sessionCreated(HttpSessionEvent e) {
    users++;
  }
  public void sessionDestroyed(HttpSessionEvent e) {
    users--;
  }
  public static int getConcurrentUsers() {
    return users;
  }
}

