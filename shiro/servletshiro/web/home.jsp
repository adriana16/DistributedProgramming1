<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<body>

  <h1>Autentificare (Apache Shiro) </h1>

  <p>Salut  
      <shiro:guest>Guest</shiro:guest>
      <shiro:user><shiro:principal/></shiro:user>!
      
      <shiro:user>
        <table>
          <tr>          
            <td>
              <a href="<c:url value="/accesAutorizat/alegeActiune.jsp"/>">Ac&#355;iuni</a>
            </td>
          </tr>
          <tr>
            <td>
              <a href="<c:url value="/logout"/>">Log out</a>
            </td>
          </tr>         
        </table>  
      </shiro:user>
      <shiro:guest>
        <a href="<c:url value="/login.jsp"/>">Log in</a> 
      </shiro:guest> 
  </p>

  <p>
      <shiro:hasRole name="admin">admin<br/>
        <%
          session.setAttribute("rol","admin");
        %>
      </shiro:hasRole>
      <shiro:hasRole name="rolCmmdc">rolCmmdc<br/>
        <%
          session.setAttribute("rol","rolCmmdc");
        %>
      </shiro:hasRole>
      <shiro:hasRole name="rolHello">rolHello<br/>
        <%
          session.setAttribute("rol","rolHello"); 
        %>
      </shiro:hasRole>
  </p>

  <h3>Roluri pe care nu le ave&#355;i</h3>

  <p>
      <shiro:lacksRole name="admin">admin<br/></shiro:lacksRole>
      <shiro:lacksRole name="rolCmmdc">rolCmmdc<br/></shiro:lacksRole>
      <shiro:lacksRole name="rolHello">rolHello<br/></shiro:lacksRole>
  </p>
  
  <h3>Activit&#259;&#355;ile d-voastr&#259;</h3>

  <p>
      <shiro:hasPermission name="all">all<br/>
         <%
          session.setAttribute("act","all");
        %>
      </shiro:hasPermission>
      <shiro:hasPermission name="cmmdc">cmmdc<br/>
         <%
          session.setAttribute("act","cmmdc");
        %>
      </shiro:hasPermission>
      <shiro:hasPermission name="hello">hello<br/>
        <%
          session.setAttribute("act","hello");
        %>
      </shiro:hasPermission>
  </p>

  <h3>Activit&#259;&#355;i de care nu dispune&#355;i</h3>

  <p>
      <shiro:lacksPermission name="cmmdc">cmmdc<br/></shiro:lacksPermission>
      <shiro:lacksPermission name="hello">hello<br/></shiro:lacksPermission>
  </p>
</body>
</html>
