<%@page import="com.telefonica_chile.bandeja.web.acciones.claves.RecuperarClaveAcc" %>
<html>
<head>
<title>@Tiempo Servicios de Banda Ancha - Recuperar Contraseña</title>
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<script src="/bandejaweb/js/jslib.js"></script>
</HEAD>
<body bgcolor="#FFFFFF" text="#000000">
<table width="780" border="0" cellspacing="0" cellpadding="0">
<form name="_modPwd" method="post" action="recuperarClave.free">
	<input type="hidden" name="accion" value="<%= RecuperarClaveAcc.GRABAR_CAMBIOS %>">
    <tr> 
      <td class="titulo-tabla">Recuperar de Contraseña</td>
    </tr>
    <tr> 
      <td class="elementos-filtro"> 
        <table class="elementos-filtro" align="center">

        <tr class="elementos-filtro">
          <td align="center" colspan=2>Si olvidó su Contraseña, ingrese su nombre de usuario y el email registrado en el sistema y la contraseña le será enviada por email</td>
        </tr>
        <tr class="elementos-filtro">
          <td align="right">Nombre de Usuario : &nbsp;&nbsp;</td>
          <td><input type="text" name="username" maxlength=30></td>
        </tr>
        <tr class="elementos-filtro">

          <td align="right">Email : &nbsp;&nbsp;</td>
          <td><input type="text" name="email" maxlength=90></td>
        </tr>
        <tr>
          <td class="elementos-filtro" align="center" colspan=2>
            <input class="botones-chicos" type="button" onClick="validar(this.form);" value="    Recuperar Contraseña    ">
          </td>
        </tr>
        </table>
      </td>
    </tr>
	<tr>
		<td>
			<jsp:include page="/jsp/mensajes.jsp" flush="true" />
		</td>
	</tr>
</form>
</table>
<SCRIPT language="JavaScript">
<!--
 function validar(forma) {
   if ( isEmpty(forma.username.value) )
     return warnEmpty(forma.username, "Revise el Nombre de Usuario");
   if ( isEmpty(forma.email.value) || !isEmail(forma.email.value))
     return warnEmpty(forma.email, "Revise el Email");
   
   forma.submit();
   return true;
 }    
//-->
</SCRIPT>

</BODY>
</HTML>
