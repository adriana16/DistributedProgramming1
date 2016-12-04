<html>
<body>
<P>
<% 
    out.println( "<BR>Your machine's address is " );
    out.println( request.getRemoteHost());
%>

<%@ page import="java.util.*" %>

<% 
    Properties p=System.getProperties();
    Enumeration e=p.propertyNames();
    while(e.hasMoreElements()){
%>
<BR>
<%
       String name=(String)e.nextElement();
       out.println(name+" : "+p.getProperty(name));
    }
%>
</body>
</html>
