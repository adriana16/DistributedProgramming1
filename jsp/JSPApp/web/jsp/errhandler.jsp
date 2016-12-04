<%@ page errorPage="errorpage.jsp" %>
<html>
<body>

<%
    String materia=request.getParameter("materia").trim();
    if (materia.equals("AN")) {
        out.println("<hr><font color=red>Alegere corecta !</font>"); 
    } 
	else {
         throw new Exception("N-ati facut alegerea corecta ");
    }
%>
</body>
</html>