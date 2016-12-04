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
      String nume=request.getParameter("name");
      out.println("Hi "+nume+" !"); 
    %>
    </center>
  </body>
</html>
