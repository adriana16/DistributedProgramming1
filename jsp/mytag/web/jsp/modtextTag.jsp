<html>
<head>
<title> Tag cu corp </title>
</head>
<body>
<%@ taglib uri="http://cs.unitbv.ro/mytaglib" prefix="mk" %>
<%
  String text=request.getParameter("text");
  String trans=request.getParameter("trans");
  String t;
  if(trans.equals("upperCase"))
    t="true";
  else
    t="false";
%>
<p>
<mk:modTag text="<%= text %>" trans="<%=t%>">
   <p/><mk:dateTag/>
</mk:modTag>
</body>
</html>