import java.util.*;
class MsgAttach{
  public static void main(String[] args){
    String file_location=System.getProperty("file_location");
    String image_location=System.getProperty("image_location");
    MsgSOAPPublisher publisher=new MsgSOAPPublisher(file_location,image_location);   
    MsgSOAPSubscriber subscriber=new MsgSOAPSubscriber();
    publisher.start();
    subscriber.start();
	}
}
