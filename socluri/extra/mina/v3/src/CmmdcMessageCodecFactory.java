import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.core.session.IoSession;

public class CmmdcMessageCodecFactory implements ProtocolCodecFactory {
  private ProtocolEncoder encoder;
  private ProtocolDecoder decoder;

  public CmmdcMessageCodecFactory() {
    encoder = new CmmdcMessageEncoder();
    decoder = new CmmdcMessageDecoder();
  }

  public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
    return encoder;
  }

  public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
    return decoder;
  }
}