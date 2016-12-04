<html>
  <body>
    <H1> CMMDC </H1>
    <%!
       long cmmdc(long m,long n){
       long c,r;
       do{
         c=n;
         r=m%n;
         m=n;
         n=r;
       }while(r!=0);
       return c;
       }
    %>
    Rezultatul este 
    <% 
       String sm=request.getParameter("m");
       String sn=request.getParameter("n");
       long m=Long.parseLong(sm),n=Long.parseLong(sn);
       out.println(cmmdc(m,n));
    %>
    </body>
</html>
