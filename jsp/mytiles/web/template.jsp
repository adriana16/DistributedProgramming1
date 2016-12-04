<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <body style="width:100%;height:100%">
    <table border="1" cellspacing="0" cellpadding="0" style="width:100%;height:100%">
      <tr>
        <td colspan="2">
         <tiles:insertAttribute name="antet" />
        </td>
      </tr>
      <tr>
        <td>
          <tiles:insertAttribute name="meniu" />
        </td>
        <td>
          <tiles:insertAttribute name="corp" />
        </td>
      </tr>
    <tr>
      <td colspan="2">
        <tiles:insertAttribute name="subsol" />
      </td>
    </tr>
    </table>
  </body>
</html>
