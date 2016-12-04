package server.impl;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AppThread{
  // Firul de executie lansat de server
  interface ServerSocketAction{
    Thread service(Socket socket);
  }

  static ServerSocketAction action=(Socket socket)->{
    return new Thread(()->{
      try(DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())){  
        long m=0,n=0,r;
        m=in.readLong();
        n=in.readLong();
		    App app=new App();
        r=app.cmmdcService.cmmdc(m,n);
        out.writeLong(r);
        socket.close();
      }
      catch(IOException e){
         e.printStackTrace();
      }  
    });
  };  
}
