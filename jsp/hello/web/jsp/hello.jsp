<jsp:useBean id="obj" class="jsp.HelloBean" scope="request"/>
<jsp:setProperty name="obj" property="*"/>
<html>
  <head>
    <title> jsphello </title>
  </head>
  <body>
    <center>
    <form method="post">
      <p> <h3> Introduceti numele: </h3>
      <input type="text" name="name" size=20>
      <p>
      <input type="submit">
    </form>
    <p>
    <% 
      out.println("Hi "+obj.getName()+" !"); 
    %>
    </center>
  </body>
</html>
