package cmmdciiop;
import javax.rmi.PortableRemoteObject;
import cmmdc.ICmmdc;
import java.rmi.RemoteException;

// Se extinde clasa PortableRemoteObject 
// si nu UnicastRemoteObject

public class CmmdcImpl extends PortableRemoteObject
                       implements ICmmdc{

  // Constructorul clasei

    public CmmdcImpl() throws RemoteException {}
       


    public long cmmdc(long a,long b){
    if (a==b)
       return a;
    else
       if (a<b)
             return cmmdc(a,b-a);
       else
           return cmmdc(a-b,b);
    }
}