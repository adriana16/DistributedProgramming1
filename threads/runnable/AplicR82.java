import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AplicR82{
  static final int NTHREADS=100;
  static ExecutorService exec=Executors.newFixedThreadPool(NTHREADS);

  interface MyThread{
    Thread scrie(String txt);
  }
  
  static MyThread f=(String txt)->{
    return new Thread(()->{
      System.out.println(txt);
    });
  };
  
  public static void main(String args[]){
    exec.execute(f.scrie("Primul fir de executie"));
    exec.execute(f.scrie("Al doilea fir de executie"));
    exec.execute(f.scrie("Al treilea fir de executie"));
    exec.shutdown();
  }
}

