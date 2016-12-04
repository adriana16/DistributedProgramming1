import com.lmax.disruptor.EventFactory;
import java.net.Socket;
 
public final class ValueEvent {
  private long _value;
  
  private Socket socket;
  
  public void setSocket(Socket socket){
    this.socket=socket;
  }
  public Socket getSocket(){
    return socket;
  }    
  /*
  public long getValue() {
    return _value;
  }

  public void setValue(long value) {
    _value = value;
  }
  */
  public final static EventFactory<ValueEvent> EVENT_FACTORY =
    new EventFactory<ValueEvent>(){
      public ValueEvent newInstance(){
        return new ValueEvent();
      }
    };
}