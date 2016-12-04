import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class CmmdcClientHandler extends IoHandlerAdapter{

  private long m, n;

  public CmmdcClientHandler(long m, long n) {
    this.m = m;
    this.n = n;
  }

  @Override
  public void sessionOpened(IoSession session) {
    String message = m + " " + n;
    System.out.println("Mesajul trimis : "+message);
    session.write(message);
  }

  @Override
  public void messageReceived(IoSession session, Object message) {
    String result = message.toString();
    if(result.length()>0) {
      System.out.println("Rezultatul este: "+result);
      session.close(true);
    }
  }

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) {
    session.close(true);
  }
}