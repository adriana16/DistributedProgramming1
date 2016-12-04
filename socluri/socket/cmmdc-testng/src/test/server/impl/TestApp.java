package server.impl;
import org.testng.annotations.*;
import org.testng.Assert;

public class TestApp{
  private App app;
  
  @BeforeClass
  public void initializare(){
    app=new App(); 
  }
  
  @Test (groups={"base"})
  public void test(){  
    Assert.assertEquals(8,app.cmmdcService.cmmdc(56,24));
  }
  
}