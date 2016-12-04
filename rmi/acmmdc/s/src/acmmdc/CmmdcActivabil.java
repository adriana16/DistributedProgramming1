package acmmdc;
import java.rmi.*;
import java.rmi.activation.*;
import cmmdc.*;

public class CmmdcActivabil extends Activatable 
    implements ICmmdc{

  public CmmdcActivabil(ActivationID id,MarshalledObject data)
	  throws RemoteException{
	super(id, 0);
  }

// Implementarea metodei declarate in ICmmdc

   public long cmmdc(long m,long n){
    if (m==n)
       return m;
    else
       if (m<n)
          return cmmdc(m,n-m);
       else
          return cmmdc(m-n,n);
    }

}

