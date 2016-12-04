<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<BODY>
<p>
<c:if test="${empty param.nume}" var="testNume" >
   <c:out value="Numele lipseste !" />
</c:if>
<c:if test="${not testNume}" >
   Nume:<c:out value="${param.nume}" />
</c:if>
<p>
<c:if test="${empty param.prenume}" var="testPrenume" >
   <c:out value="Prenumele lipseste !" />
</c:if>
<c:if test="${not testPrenume}" >
   Prenume:<c:out value="${param.prenume}" />
</c:if>
<p>
<c:if test="${empty param.email}" var="testEmail">
   <c:out value="Adresa E-Mail lipseste !" />
</c:if>
<c:if test="${not testEmail}" >
   E-mail:<c:out value="${param.email}" />
</c:if>

<h2> Lista parametrilor din formular </h2>
<ul>
  <c:forEach items="${param}" var="p">
    <li>
      <c:out value="${p.key}"/> = <c:out value="${p.value}" />
    </li>
  </c:forEach>
</ul>

<c:import url="http://localhost:8080/JSPApp/jsp/JSTLCore1.jsp" />
</BODY>
</HTML>
