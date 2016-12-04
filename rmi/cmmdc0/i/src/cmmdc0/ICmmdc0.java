package cmmdc0;
public interface ICmmdc0 extends java.rmi.Remote{
	public long compute(long m,long n) throws java.rmi.RemoteException;
	public void setMethod(ICallbackCmmdc obj)throws java.rmi.RemoteException;
}
