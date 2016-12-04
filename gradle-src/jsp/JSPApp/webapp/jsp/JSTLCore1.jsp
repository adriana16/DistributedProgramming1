<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<BODY>

<h2> Lista campurilor din antet </h2>
<ul>
  <c:forEach items="${header}" var="h">
    <li>
      <c:out value="${h.key}" /> = <c:out value="${h.value}" />
    </li>
  </c:forEach>
</ul>
<h2> Alt exemplu </h2>
<c:forEach begin="1" end="6" var="i" >
   <p>
   <c:out value="<h${i}> Heading ${i} </h${i}>" escapeXml="false" />
</c:forEach>
</BODY>
</HTML>
