<!--
Aceste comentarii sunt foarte importante in cazul utilizarii navigatorului IE 
si a lui apache-tomcat-5.*.*. In lipsa lor nu se genereaza saltul la exceptie
prin pagina jsp.
Rolul comentariilor este marirea lungimii fisierului de fata.

O alternativa este ca din IE6 . . . Options sa se dezactiveze optiunea 
"Show friendly HTTP error message"

Cu navigatorul Firefox nu exista aceasta problema.
-->

<%@ page isErrorPage="true" %>
<html>
<head></head>
<body>
<div align="center">
<%= exception.getMessage() %>
</div>
</body>
</html>
