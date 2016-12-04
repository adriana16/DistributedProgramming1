<jsp:useBean id="obj" class="cmmdc.CmmdcBean" scope="application"/>
<jsp:setProperty name="obj" property="*"/>
<html>
<body>
   Cel mai mare divizor comun al numerelor
   <p>
   <%=obj.getM() %> si <%=obj.getN() %>
   este <%=obj.getCmmdc() %>
</body>
</html>
