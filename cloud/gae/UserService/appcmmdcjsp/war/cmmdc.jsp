<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
  <body>
  <%!
    public long cmmdc(long m, long n){
      long r,c;
      do{
        c=n;
        r=m%n;
        m=n;
        n=r;
      }while(r!=0);
      return c;
    }   
  %>
  <%
      UserService userService = UserServiceFactory.getUserService();
      User user = userService.getCurrentUser();
      if (user != null) {
  %>
        <h1> Calculul CMMDC </h1>
        <p/>
        Hello, <%= user.getNickname() %>!
        <p/>
        Introduce&#355;i:
        <form method="post">
          <table border="1">
             <tr>
                <td> Primul num&#259;r </td>
                <td> <input type="text" name="m" size="20" value="1" /> <td>
             </tr>
             <tr>
                <td> Al doilea num&#259;r </td>
                <td> <input type="text" name="n" size="20" value="1" /> <td>
             </tr>      
             <tr>
                <td> <input type="submit" value="Calculeaza"/> </td>
        <%
          String sm=request.getParameter("m"),sn=request.getParameter("n");  
          //out.println(sm+" "+sn);
          long x=0;
          if(sm==null){
            //response.sendRedirect(request.getRequestURI());
            sm="1";
            sn="1";
          }  
          //else{             
            long m=Long.parseLong(sm),n=Long.parseLong(sn);  
            x=cmmdc(m,n);
          //}
        %>
                <td> (<%=sm%>,<%=sn%>)=<%=x%> </td>
             </tr> 
          </table>
        </form>
 
        <p>
        <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign out</a>
  <%
    }  
    else{
  %>
     <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
  <%
    }
  %>
  </body>
</html>
