import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class AplicC81{
  interface MyCallable{
    Callable<Integer> scrie(int index);
  }
  
  static MyCallable f=(int index)->{
    Callable<Integer> c=()->{
      System.out.println("I am "+index);
      return index;
    };
    return c;
  };

  public static void main(String args[]){
    int numarFire=20;  
    if(args.length>0)
      numarFire=Integer.parseInt(args[0]);
    ExecutorService executor=Executors.newCachedThreadPool();
    Future<?>[] v=new Future[numarFire];
    for(int i=0;i<numarFire;i++){
      v[i]=executor.submit(f.scrie(i)); 
    }
    for(int i=0;i<numarFire;i++) {
      try {
        System.out.format("S-a terminat firul: %d%n",v[i].get());
      }
      catch (Exception e) {}
    }
    try{
      executor.shutdown();
    }
    catch(Exception e){}  
  }        
}
