package simple.app.cmmdc;
import org.junit.*;
import static org.junit.Assert.*;

public class TestProiect{
  MyCmmdc obj;
  long rez;
  
  @Before
  public void setUp(){
    obj=new MyCmmdc() ;  
  }
 
  @Test
  public void testCmmdc1( ){
    //obj=new MyCmmdc() ;
    rez=obj.cmmdcService.cmmdc(56,42);
    assertEquals(14l,rez);
  }
  
  @Test
  public void testCmmdc2( ){
    //obj=new MyCmmdc();
    rez=obj.cmmdcService.cmmdc(45,31);
    assertEquals(1,rez);
  }

  public static void main(String args[ ]){
    org.junit.runner.JUnitCore.main("proiect.TestProiect");
  }
}
