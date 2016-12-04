<%
   String mesaj;
   String sursa=request.getRemoteHost();
   Integer contor=(Integer)session.getAttribute("noAcces");
   if(contor==null)
      {
       contor=new Integer(1);
       mesaj="Salut "+sursa+" !";
      }
   else
      { 
       contor=new Integer(contor.intValue()+1);
       mesaj="Bine ati revenit "+sursa+" !";
      }
   session.setAttribute("noAcces",contor);  
%>
<html>
<body>
<p>
  <%= mesaj %>
</p>
<p>
Numarul de accesari al acestei pagini este
<%= ((Integer)session.getAttribute("noAcces")).intValue() %>
</p>

</body>
</html>