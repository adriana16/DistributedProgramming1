import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSession;
import javax.net.ssl.KeyManagerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer{
  static final int NTHREADS=1024;
  static ExecutorService exec=Executors.newFixedThreadPool(NTHREADS);
  
  public static void main(String[] args) {
    if (args.length<3) {
       System.out.println("Usage:");
       System.out.println(
          "   java SslReverseEchoerRevised serverKeystore serverStorepass serverKeypass");
       System.exit(0);
    }
    String ksName = args[0];
    char[] ksPass = args[1].toCharArray();
    char[] ctPass = args[2].toCharArray();
    System.setProperty("javax.net.ssl.trustStore",args[0]);
    System.setProperty("javax.net.ssl.trustStorePassword",args[1]);
    try {
      KeyStore ks = KeyStore.getInstance("JKS");
      ks.load(new FileInputStream(ksName), ksPass);
      KeyManagerFactory kmf=KeyManagerFactory.getInstance("SunX509");
      kmf.init(ks, ctPass);
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(kmf.getKeyManagers(), null, null);
      SSLServerSocketFactory ssf = sc.getServerSocketFactory();
      SSLServerSocket s= (SSLServerSocket)ssf.createServerSocket(7999);
      s.setNeedClientAuth(true);
      printServerSocketInfo(s);
      SSLSocket c = (SSLSocket) s.accept();
      printSocketInfo(c);
    
      System.out.println("The server is listening on port 7999");
      DataOutputStream out = new DataOutputStream(c.getOutputStream());
      DataInputStream in = new DataInputStream(c.getInputStream());
      App app=new App();
      long m=0,n=0,r;  
      boolean listening=true;
      while(listening){                 
        m=in.readLong();
        n=in.readLong();
        r=app.cmmdc(m,n);
        out.writeLong(r);
      }
      out.close();
      in.close();
      c.close();
      s.close();
    } 
    catch(Exception e) {
      System.err.println(e.toString());
    }
  }

  private static void printSocketInfo(SSLSocket s) {
      System.out.println("Socket class: "+s.getClass());
      System.out.println("   Remote address = "
         +s.getInetAddress().toString());
      System.out.println("   Remote port = "+s.getPort());
      System.out.println("   Local socket address = "
         +s.getLocalSocketAddress().toString());
      System.out.println("   Local address = "
         +s.getLocalAddress().toString());
      System.out.println("   Local port = "+s.getLocalPort());
      System.out.println("   Need client authentication = "
         +s.getNeedClientAuth());
      SSLSession ss = s.getSession();
      try {
         System.out.println("Session class: "+ss.getClass());
         System.out.println("   Cipher suite = "
            +ss.getCipherSuite());
         System.out.println("   Protocol = "+ss.getProtocol());
         System.out.println("   PeerPrincipal = "
            +ss.getPeerPrincipal().getName());
         System.out.println("   LocalPrincipal = "
            +ss.getLocalPrincipal().getName());
      } catch (Exception e) {
         System.err.println(e.toString());
      }
   }
   
   private static void printServerSocketInfo(SSLServerSocket s) {
      System.out.println("Server socket class: "+s.getClass());
      System.out.println("   Socker address = "
         +s.getInetAddress().toString());
      System.out.println("   Socker port = "
         +s.getLocalPort());
      System.out.println("   Need client authentication = "
         +s.getNeedClientAuth());
      System.out.println("   Want client authentication = "
         +s.getWantClientAuth());
      System.out.println("   Use client mode = "
         +s.getUseClientMode());
   } 

}
