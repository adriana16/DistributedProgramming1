import java.lang.reflect.Method;

public class TestAnnotation{
  public static void main(String[] args){
    TestAnnotation obj=new TestAnnotation();
    obj.verif(obj);
  }
  
  @MyAnnotation(doAction="XYZ",index=1)
  public void verif(Object o){
    try{
      Class cl=o.getClass();
      Method m=cl.getMethod("verif",new Class[]{(new Object()).getClass()});
      if(m.isAnnotationPresent(MyAnnotation.class)){
        MyAnnotation a=m.getAnnotation(MyAnnotation.class);
        if(a!=null){
          String numeElement=a.doAction();
          System.out.println(numeElement);
          int index=a.index();
          System.out.println(index);
        }
      }
    }
    catch(Exception e){
      System.out.println("MyEx : "+e.getMessage());
    }
  }
}