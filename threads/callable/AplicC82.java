import java.util.concurrent.Callable;

public class AplicC82{
  interface MyCallable{
    Callable<Integer> scrie(int index) throws Exception;
  }
  
  static MyCallable f=(int index)->{
    Callable<Integer> c=()->{
      System.out.println("I am "+index);
      return index;
    };
    return c;
  };

  public static void main(String args[]){
    int numarFire=3;
    try{
      for(int i=0;i<numarFire;i++){
        Integer r=f.scrie(i).call();
        System.out.println("Returned : "+r);
      }
    }
    catch(Exception e){
      System.out.println("Exception : "+e.getMessage());
    }    
  }        
}
