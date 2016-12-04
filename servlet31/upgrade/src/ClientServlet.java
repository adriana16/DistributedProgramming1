package cmmdc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/client"})
public class ClientServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req,HttpServletResponse res)
          throws ServletException, IOException {
    String m=req.getParameter("m");
    String n=req.getParameter("n");
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    final String CRLF = "\r\n";
    final String host = req.getServerName();// "localhost";
    final int port = req.getServerPort(); // 8080;
    final String contextRoot = "/upgrade";
    final String data = m+" "+n;
    InputStream input = null;
    OutputStream output = null;
    BufferedReader reader = null;
    Socket s = null;
    try{
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ClientTest</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Http Upgrade Process</h1>");

      // Setting the HTTP upgrade req header
      String reqStr = "POST " + contextRoot + "/upgrade HTTP/1.1" + CRLF;
      reqStr += "User-Agent: Java/1.7" + CRLF;
      reqStr += "Host: " + host + ":" + port + CRLF;
      reqStr += "Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" + CRLF;
      reqStr += "Upgrade: upgrade" + CRLF;
      reqStr += "Connection: Upgrade" + CRLF;
      reqStr += "Content-type: application/x-www-form-urlencoded" + CRLF;
      reqStr += "Transfer-Encoding: chunked" + CRLF;
      reqStr += CRLF;
      reqStr += data + CRLF;

      s = new Socket(host, port);
      input = s.getInputStream();
      output = s.getOutputStream();
      output.write(reqStr.getBytes());
      output.flush();

      out.println("<h2>Sending upgrade req to server......</h2>");
      out.println("<h3>Request header with data:</h3>");
      out.println();

      String reqStrDisplay = reqStr.replaceAll("\r\n", "</br>");
      out.println(reqStrDisplay);
      out.println("--------------------------------------- </br></br>");
      out.flush();

      reader = new BufferedReader(new InputStreamReader(input));

      out.println("<h2>Server accept upgrade req, send back the res:</h2>");
      out.println("<h3>Response header:</h3>");
      out.println();

      // Reading the res, and displaying the header from server
      printHeader(reader, out);
      out.println("--------------------------------------- </br></br>");
      out.flush();

      out.println("<h2>Server send back the res with new protocol and data:</h2>");
      out.println("<h3>Response header with data:</h3>");

      // Reading the res, and displaying the header from server
      printHeader(reader, out);

      // Reading the echo data
      String dataOutput;
      if ((dataOutput = reader.readLine()) != null) {
        // Print out the data after header
        out.println("</br>" + dataOutput + "</br>");
        out.println("--------------------------------------- </br></br>");
        out.println("<h2>Connection with new protocol established</h2>");
      }
      out.flush();
      out.println("</body>");
      out.println("</html>");
    } 
    catch (IOException e) {
      System.out.println("ClientServlet Exception : "+e.getMessage());   
    } 
    finally{
      if (reader != null) {
          reader.close();
      }
      if (output != null) {
          output.close();
      }
      if (input != null) {
          input.close();
      }
      if (s != null) {
          s.close();
      }
    }
  }

  protected void printHeader(BufferedReader reader, PrintWriter out) throws IOException {
    for(String line; (line = reader.readLine()) != null;) {
      if(line.isEmpty()){
        break;
      }
      out.println(line + "</br>");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req,HttpServletResponse res)
    throws ServletException, IOException {
    doGet(req, res);
  }
}
