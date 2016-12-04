package cmmdc0;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class RemoteClient extends UnicastRemoteObject
  implements ICallbackCmmdc{

  ICmmdc0 remote=null;

  public RemoteClient() throws RemoteException{
	  //super();
  }

  public String getMethod(){
    Scanner scanner=new Scanner(System.in);
    System.out.println("Alegeti una din variantele algoritmului lui Euclid");
    System.out.println("1  - algoritmul ne-recursiv");
    System.out.println("2  - algoritmul recursiv");
    int x=scanner.nextInt();
    String method=null;
    if(x==1)
      method="NERECURSIV";
    else
      method="RECURSIV";
    return method;
  }

  public RemoteClient(String host,int port)throws RemoteException{
    try{
      Registry registry=LocateRegistry.getRegistry(host,port);
      IFabObiecte obj=(IFabObiecte)registry.lookup("ObjectFactory");
      remote=obj.getCmmdc();
    }
    catch(Exception e){
      System.out.println("ClientException: "+e.getMessage());
    }
  }
}