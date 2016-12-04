<html>
  <head>
    <title> Tag cu marcaj </title>
  </head>
  <body>
  <%@ taglib uri="mytags" 
      prefix="mk" %>
    <p>
    <% 
      String zi=request.getParameter("ziua");
    %>
    Ziua este:
    <mk:ziuaTag ziua="<%=zi%>" />
  </body>
</html>