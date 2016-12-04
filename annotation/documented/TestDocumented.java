public class TestDocumented{
  public static void main(String[]args){
    new TestDocumented().doDocumented();
  }
  
  @MyDocumented
  public void doDocumented(){
    System.out.println("Test Documented");
  }
}