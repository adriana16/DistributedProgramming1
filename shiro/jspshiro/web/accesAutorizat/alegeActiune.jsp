<html>
 <body bgcolor="#aaeeaa">
   <table>
     <%
       String act=(String)session.getAttribute("act");
       if(act=="all"){
     %>
     <tr>
       <td>
         <a href="alege.html">Actiuni administrator</a>
       </td>
     </tr>  
     <%
       } 
       if(act=="cmmdc"){
     %>    
       <tr>
         <td>
           <a href="cmmdc1pagina.jsp">Calcul cmmdc</a>
         </td>
     </tr>  
     <%    
       }
    
     if(act=="hello"){
     %>    
       <tr>
         <td>
           <a href="hello1pagina.jsp">Aplicatia Hello Name</a>
         </td>
     </tr>  
     <%    
       }
     %>
    </table>
 </body
</html> 