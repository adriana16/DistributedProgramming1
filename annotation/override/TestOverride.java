import java.util.Date;
public class TestOverride extends Date{
  @Override
  public String toString(){
    return super.toString()+" TestOverride";
  }
  
  public static void main(String[]args){
    Date d=new Date();//TestOverride();
    System.out.println(d.toString());
  }
}