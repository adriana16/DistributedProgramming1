import java.util.*;
class MsgAttach{
  public static void main(String[] args){
    String file_location=System.getProperty("file_location");
    String image_location=System.getProperty("image_location");
    String mp3_location=System.getProperty("mp3_location");
    String video_location=System.getProperty("video_location");
    MsgSOAPPublisher publisher=new MsgSOAPPublisher(file_location,image_location,mp3_location,video_location);   
    MsgSOAPSubscriber subscriber=new MsgSOAPSubscriber();
    publisher.start();
    subscriber.start();
  }
}
