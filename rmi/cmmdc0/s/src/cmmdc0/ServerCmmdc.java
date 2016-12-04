package cmmdc0;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerCmmdc extends UnicastRemoteObject
	implements ICmmdc0{

  private String method=null;
  
  public ServerCmmdc()throws RemoteException{}
  
  public long compute(long m,long n)throws RemoteException{
  	long x=0;
  	if(method.equals("NERECURSIV"))
  		x=nerecursiv(m,n);
  	if(method.equals("RECURSIV"))
  		x=recursiv(m,n);
  	return x;
  }
  
  public void setMethod(ICallbackCmmdc obj)throws RemoteException{
  	method=obj.getMethod();
  }
  
  private long recursiv(long a,long b){
    if (a==b)
       return a;
    else
       if (a<b)
             return recursiv(a,b-a);
       else
           return recursiv(a-b,b);
    }

  private long nerecursiv(long m,long n){
    long r,c;
    do{
      c=n;
      r=m%n;
      m=n;
      n=r;
    }
    while(r!=0);
    return c;
  }

}

      
