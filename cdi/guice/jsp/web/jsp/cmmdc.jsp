<jsp:useBean id="appModule" class="jsp.AppModule" scope="application"/>
<%@page import="com.google.inject.Guice,com.google.inject.Injector,service.ICmmdc,service.impl.CmmdcImpl" %>
<!doctype html>
  <body>
    <H1> CMMDC </H1>
    <form method="post">
      <table> 
        <tr>
          <td><label> Primul numar </label></td>
          <td>    
             <input type="number" name="m" size="5" value="1"
             required min="1"/>         
          </td>
        </tr>  
        <tr>
          <td><label> Al doilea numar </label></td>
          <td>
             <input type="number" name="n" size="5" value="1"
             required min="1"/>
          </td>
        </tr>
        <tr>
          <td>          
             <p><input type="submit" value="Calculeaza">
          </td>
        </tr>
      </table>  
    </form>
    Rezultatul este 
    <% 
       String sm=request.getParameter("m");
       String sn=request.getParameter("n");
       System.out.println(sm+" : "+sn);          
       if(sm==null) sm="1";
       if(sn==null) sn="1";
       long m=Long.parseLong(sm.trim());
       long n=Long.parseLong(sn.trim());
       Injector injector = Guice.createInjector(appModule);    
       ICmmdc obj = injector.getInstance(ICmmdc.class);
       out.println(obj.cmmdc(m,n));   
    %>
  </body>
</html>
