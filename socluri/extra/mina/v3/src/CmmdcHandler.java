import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class CmmdcHandler extends IoHandlerAdapter{
  @Override
  public void exceptionCaught( IoSession session, Throwable cause ) throws Exception{
    cause.printStackTrace();
  }

  @Override
  public void messageReceived( IoSession session, Object message ) throws Exception{
    if(message instanceof CmmdcMessage){
      CmmdcMessage msg = (CmmdcMessage) message;
      App obj=new App();
      long rez = obj.cmmdc(msg.getM(),msg.getN());
      msg.setResult(rez);
      session.write(msg);
    }
  }
}