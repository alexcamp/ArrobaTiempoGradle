<%@page import="com.telefonica_chile.bandeja.web.acciones.claves.CambiarClaveAcc" %>
<%@page import="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" %>
<%@page import="com.telefonica_chile.bandeja.web.UsuarioWeb" %>
<jsp:useBean id="controladorDeAplicacion" class="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" scope="session" />
<form name="_modPwd" method="post" action="cambiarClave.acc">
<input type="hidden" name="accion" value="<%= CambiarClaveAcc.GRABAR_CAMBIOS %>">
<table width="780" border="0" cellspacing="0" id="detalle_basico" cellpadding="0">
    <tr> 
      <td width="190" class="titulo-tabla" height="15">Modificación de Password</td>
      <td class="titulo-tabla" height="15" width="289">&nbsp;</td>
      <td width="314" class="titulo-tabla" height="15" align="right">
      </td>
    </tr>
    <tr> 
      <td colspan="3" class="elementos-filtro" id="celda_tabD_1"> 
      <center><font class="alerta"></font></center>

        <table class="elementos-filtro" align="center">
        <tr>
          <td class="elementos-filtro" align="right">Usuario:&nbsp;&nbsp;</td>
          <td class="elementos-filtro" width="157"><%= controladorDeAplicacion.getUsuario().getUsername() %></td>
        </tr>
        <tr>
          <td class="elementos-filtro" align="right">Clave Antigua:&nbsp;&nbsp;</td>

          <td class="elementos-filtro" width="157"><input type="password" name="oldClave" maxlength=30  ></td>
        </tr>
        <tr>
          <td class="elementos-filtro" align="right">Clave Nueva:&nbsp;&nbsp;</td>
          <td class="elementos-filtro" width="157"><input type="password" name="newClave" maxlength=30 ></td>
        </tr>
        
        <tr>
          <td class="elementos-filtro" align="right">Repita la Clave Nueva&nbsp;&nbsp;:</td>
          <td class="elementos-filtro" width="157"><input type="password" name="newClave2" maxlength=30 ></td>
        </tr>
        <tr>
          <td class="elementos-filtro" align="center" colspan=2>
            <input class="botones-chicos" type="button" onClick="validar(this.form);" value="    Modificar Password    ">
          </td>
        </tr>
        <tr>
          <td class="elementos-filtro" colspan="2">
          <jsp:include page="/jsp/mensajes.jsp" flush="true" />
          </td>
        </tr>
        </table>
      </td>

    </tr>
</table>
</form>
<SCRIPT language="JavaScript">
<!--
 function validar(forma) {
   if ( isEmpty(forma.oldClave.value) )
     return warnEmpty(forma.oldClave, "Debe ingresar la clave Antigua");
   if ( isEmpty(forma.newClave.value) )
     return warnEmpty(forma.newClave, "Debe ingresar una nueva Clave");
   
   if ( forma.oldClave.value == forma.newClave.value )
     return warnInvalid(forma.newClave, "La Nueva Clave debe ser distinta a la Anterior");

   if ( forma.newClave.value != forma.newClave2.value ) {
     return warnInvalid(forma.newClave2, "La Nueva Clave repetida es distinta");
   }
   
   forma.submit();
   return true;
 }    
//-->
</SCRIPT>

</BODY>
</HTML>
