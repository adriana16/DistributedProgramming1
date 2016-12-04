<%@ page info="Pagina JSP pentru exemplificarea directivelor" 
    import="java.util.Date" %>
<html>
  <body>
    <p>
    <b>Exemplificarea atributului info</b>
    <br>
    <%= getServletInfo() %>
    <p>
    <b>Exemplificarea atributului import</b>
    <br>
    <%= new Date() %>
    <p>
    <b>Exemplificarea utilizarii elementelor jsp:include jsp:forward jsp:param</b>
    <br>
    <jsp:include page="cmmdc.jsp">
      <jsp:param name="m" value="56" />
      <jsp:param name="n" value="24" />
    </jsp:include>
    <br>
    <%= "... dupa cmmdc1" %>
    <p>
    <b> Exemplificarea directivei include </b>
    <br>
    <%@ include file="Hello.jsp" %>
    <p>
    <b>Alta exemplificare a utilizarii elementelor jsp:include jsp:forward </b>
    <br>
    <jsp:include page="cmmdc.html"/>
    <%--
      Modificati textul sursa schimband include cu forward
    --%>
  </body>
</html>
