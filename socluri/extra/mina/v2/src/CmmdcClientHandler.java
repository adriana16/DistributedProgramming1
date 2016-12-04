import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class CmmdcClientHandler extends IoHandlerAdapter {
  private long m, n;

  public CmmdcClientHandler(long m, long n) {
    this.m = m;
    this.n = n;
  }

  @Override
    public void sessionOpened(IoSession session) {
      CmmdcMessage message = new CmmdcMessage();
      message.setM(m);
      message.setN(n);
      System.out.println("Mesajul trimis : "+message.toString());
      session.write(message);
    }

  @Override
  public void messageReceived(IoSession session, Object message) {
    if(message instanceof CmmdcMessage){
      CmmdcMessage mesaj = (CmmdcMessage)message;
      System.out.println("Rezultatul este: "+mesaj.getResult());
      session.close(true);
    }
  }

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) {
    session.close(true);
  }
}