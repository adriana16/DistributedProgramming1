package jsp;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule{
  @Override
  protected void configure(){
    bind(service.ICmmdc.class).to(service.impl.CmmdcImpl.class);
  }
}