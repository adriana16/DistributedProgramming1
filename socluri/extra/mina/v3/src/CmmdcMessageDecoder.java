import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class CmmdcMessageDecoder extends CumulativeProtocolDecoder {
  protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
    if (in.remaining() >= 24) {
      long m = in.getLong();
      long n = in.getLong();
      long result = in.getLong();
      CmmdcMessage msg = new CmmdcMessage();
      msg.setM(m);
      msg.setN(n);
      msg.setResult(result);
      out.write(msg);
      return true;
    } else {
      return false;
    }
  }
}