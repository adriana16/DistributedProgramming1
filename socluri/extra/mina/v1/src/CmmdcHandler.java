import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class CmmdcHandler extends IoHandlerAdapter{
  @Override
  public void exceptionCaught( IoSession session, Throwable cause ) throws Exception{
      cause.printStackTrace();
  }

  @Override
  public void messageReceived( IoSession session, Object message ) throws Exception{
    String ls=System.getProperty("line.separator");
    String str = message.toString().trim();
  
    String[] pieces = str.split(" ");
    if(pieces.length<2){
      session.write(ls);
      session.write("Numar insuficient de parametri");
      session.write(ls);
    }
    else{
      try{
        long var1, var2;
        var1 = Long.parseLong(pieces[0]);
        var2 = Long.parseLong(pieces[1]);
        session.write(ls);
        App obj=new App();
        session.write( obj.cmmdc(var1,var2) );
        session.write(ls);
      }
      catch(NumberFormatException ex){
        session.write(ls);
        session.write("Nu s-au putut extrage numerele din sirul de caractere");
        session.write(ls);
      }
    }
  }
}