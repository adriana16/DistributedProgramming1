<html>
  <head>
    <title> Init </title>
  </head>
  <body>
    <%!
      int numar;
      public void jspInit(){
        numar=0;
      }
      public void jspDestroy(){
        System.out.println(numar);
		    //out.print("Numarul este : "+(new Integer(numar)).toString());
      }
    %>
    <center>
    <h1> Pagina de r&#259;spuns </h1>
    <p>
    <% 
      String sn=request.getParameter("numar");
      int n=Integer.parseInt(sn);
      numar+=n;
      out.println("Numarul este : "+numar);
    %>
    </center>
  </body>
</html>
