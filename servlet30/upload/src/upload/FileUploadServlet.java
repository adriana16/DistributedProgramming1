package upload;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet; 

import java.util.List;
import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItem;

@WebServlet(urlPatterns = "/upload") 

public class FileUploadServlet extends HttpServlet{
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
  throws ServletException,IOException {
    res.setContentType("text/plain");
    ServletOutputStream out = res.getOutputStream();
    try{
      //boolean isMultipart = ServletFileUpload.isMultipartContent(req);
      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload   upload = new ServletFileUpload(factory);
      List items = upload.parseRequest(req);
      upload.setSizeMax(1000000);
      Iterator iter=items.iterator();
      while (iter.hasNext()) {
        FileItem item = (FileItem) iter.next();
        if (!item.isFormField()) {
          String fileName = item.getName();
          out.println(fileName);
          long sizeInBytes = item.getSize();
          out.println(sizeInBytes);
          InputStream in=item.getInputStream();
          InputStreamReader isr=new InputStreamReader(in);
          BufferedReader br=new BufferedReader(isr);
          double[][] matrix=getMatrix(br);
          int m=matrix.length;
          int n=matrix[0].length;
          for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
              out.print(matrix[i][j]+" ");
            out.println();
          }
          br.close();
          isr.close();
          in.close();
          out.close();
        }
      }
    }
    catch(Exception e){
      System.out.println("Exception: "+e.getMessage());
    }
  }
  
  private double[][] getMatrix(BufferedReader br) throws Exception{
    Vector<Double> v=new Vector<Double>(10);
    double[][] matrix=null;
    try{    
      String line;//,s;
      int m=0,n,mn;
      do{
        line=br.readLine();
        if(line!=null){
          m++;
          String[] st=line.split(" ");
          for(String s:st){
            v.addElement(new Double(s));
          }
        }
      }
      while(line!=null);
      if(v.size()>0){
        mn=v.size();
        n=mn/m;
        matrix=new double[m][n];
        for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
            matrix[i][j]=((Double)v.elementAt(i*n+j)).doubleValue();
            System.out.print(matrix[i][j]+" ");
          }
          System.out.println();
        }
      }
    }
    catch(Exception e){
      throw new Exception(e.getMessage());
    }
    return matrix;
  }
}