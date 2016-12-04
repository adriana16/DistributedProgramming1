import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSession;
import javax.net.ssl.KeyManagerFactory;
import java.security.KeyStore;

public class CmmdcClient {
  public static void main(String[] args) {
      if (args.length<3) {
         System.out.println("Usage:");
         System.out.println(
           "   java CmmdcClient clientKeystore clientStorepass clientKeypass");
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
         SSLSocketFactory f = sc.getSocketFactory();
         SSLSocket c=(SSLSocket)f.createSocket("localhost",7999);
         printSocketInfo(c);
         c.startHandshake();
         
         Scanner scanner=new Scanner(System.in);
         long m,n,r;
         DataInputStream in=new DataInputStream(c.getInputStream());
         DataOutputStream out=new DataOutputStream(c.getOutputStream());
         
         char cont='N';
         do{
           System.out.println("m=");
           m=scanner.nextLong();
           System.out.println("n=");
           n=scanner.nextLong();
           out.writeLong(m);
           out.writeLong(n);
           r=in.readLong();
           System.out.println("Required result : "+r);
           
           System.out.println("Doriti sa continuati ? (Y/N)");
           cont=scanner.next().toUpperCase().charAt(0);
           System.out.println(cont);
         }
         while(cont=='Y');
          
         out.close();
         in.close();
         c.close();
      } 
      catch (Exception e) {
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
         System.out.println("   PeerPrincipal = "
            +ss.getPeerPrincipal().getName());
      } catch (Exception e) {
         System.err.println(e.toString());
      }
   }
}
 
