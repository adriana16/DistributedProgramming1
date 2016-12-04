import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class CmmdcServer{
  private static final int PORT = 8999;

  public static void main( String[] args ) throws Throwable{
    IoAcceptor acceptor = new NioSocketAcceptor();
    //IoAcceptor acceptor = new NioDatagramAcceptor();
    acceptor.getFilterChain().addLast( "codec", 
      new ProtocolCodecFilter( 
        new TextLineCodecFactory( Charset.forName( "UTF-8" ))));

    CmmdcHandler handler=new CmmdcHandler();
    acceptor.setHandler(handler);
    System.out.println("Server ready on port "+PORT);
    acceptor.bind(new InetSocketAddress(PORT) );
  }
}