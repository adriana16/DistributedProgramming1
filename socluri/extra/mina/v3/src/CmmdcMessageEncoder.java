import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CmmdcMessageEncoder extends ProtocolEncoderAdapter {

  public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
    if((message instanceof CmmdcMessage) == false) return;
    CmmdcMessage msg = (CmmdcMessage) message;
    IoBuffer buffer = IoBuffer.allocate(24, false);
    buffer.putLong(msg.getM());
    buffer.putLong(msg.getN());
    buffer.putLong(msg.getResult());
    buffer.flip();
    out.write(buffer);
  }
}