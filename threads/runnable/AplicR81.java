public class AplicR81{

  interface MyThread{
    Thread scrie(String txt);
  }
  
  static MyThread f=(String txt)->{
    return new Thread(()->{
      System.out.println(txt);
    });
  };
  
  public static void main(String args[]){
    f.scrie("Primul fir de executie").start();
    f.scrie("Al doilea fir de executie").start();
    f.scrie("Al treilea fir de executie").start();
  }
}

