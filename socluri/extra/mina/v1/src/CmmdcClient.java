import java.net.InetSocketAddress; 
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession; 
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.apache.mina.core.service.IoConnector;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import java.nio.charset.Charset;
import java.util.Scanner;

public class CmmdcClient{
	public static void main(String[] args) throws Throwable{
    String host="localhost";
    int port=8999;
    if (args.length>0)
       host=args[0];
    if (args.length>1)
       port=Integer.parseInt(args[1]);
    long m,n,r;
    Scanner scanner=new Scanner(System.in);
    System.out.println("m=");
    m=scanner.nextLong();
    System.out.println("n=");
    n=scanner.nextLong();

    IoConnector connector = new NioSocketConnector();
    //IoConnector connector = new NioDatagramConnector();
    connector.getFilterChain().addLast( "codec", 
      new ProtocolCodecFilter( 
        new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
    connector.setHandler(new CmmdcClientHandler(m, n));
    IoSession session = null;
   
    try{
      ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
      future.awaitUninterruptibly();
      session = future.getSession();  
    }
    catch(RuntimeIoException e) {
      System.out.println("Conectare esuata"); 
      System.exit(0);      
    }
    session.getCloseFuture().awaitUninterruptibly();
    connector.dispose();
  }
}