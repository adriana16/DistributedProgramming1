<HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<BODY>
<p>
<sql:setDataSource
  driver="org.apache.derby.jdbc.ClientDriver"
  url="jdbc:derby://localhost:1527/AgendaEMail"
  var="db" />
<sql:query
  dataSource="${db}"
  var="rezult"
  sql="select * from adrese" />
<c:if test="${rezult.rowCount gt 0}" >
  <table>
    <tr> 
    <c:forEach items="${rezult.columnNames}" var="col">
       <th>
        <c:out value="${col}" />
         </th>
      </c:forEach>
  </tr>
  <c:forEach items="${rezult.rowsByIndex}" var="line" >
    <tr>
      <c:forEach items="${line}" var="elem" >
       <td>
         <c:out value="${elem}" />
       </td>
    </c:forEach>
    </tr>
  </c:forEach>
  </table>
 </c:if>
</BODY>
</HTML>
