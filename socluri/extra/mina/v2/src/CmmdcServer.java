import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;


public class CmmdcServer{
  private static final int PORT = 8999;

  public static void main( String[] args ) throws Throwable{
    IoAcceptor acceptor = new NioSocketAcceptor();
    acceptor.getFilterChain().addLast( "codec", 
      new ProtocolCodecFilter( 
        new ObjectSerializationCodecFactory()));

    acceptor.setHandler( new CmmdcHandler() );
    System.out.println("Astept conexiuni pe portul "+PORT);
    acceptor.bind( new InetSocketAddress(PORT) );
  }
}