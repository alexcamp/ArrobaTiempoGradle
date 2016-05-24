<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.*" %>
<jsp:useBean id="controladorDeAplicacion"
	class="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" scope="session" />
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%
String cambioClave = (String)request.getAttribute("cambioClave");
UsuarioWeb usuario = controladorDeAplicacion.getUsuario();
%>
<HTML>
<HEAD>
<SCRIPT>
function jsBandeja(){
   document.frm.action = "logoff.acc";				
   document.frm.submit();
}
</SCRIPT>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Informe</TITLE>
<link href="/bandejaweb/estilo.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<form name="frm">
<table border="0" cellpadding="0" cellspacing="0" height="75" width="780">
  <tbody> 
  <tr align="right"> 
    <td colspan="2"> <img src="/bandejaweb/img/t.gif" height="10" width="1"></td>
    <td rowspan="2" class="fecha" align="right" valign="bottom" width="558"> 
      <table border="0" cellpadding="0" cellspacing="0" width="95%">
        <tbody><tr> 
            <td height="19" valign="bottom" class="txt-usuario-rol">
                <img src="/bandejaweb/img/t.gif" width="4">
                <img src="/bandejaweb/img/icono_usuario.gif">
                <%= usuario.getUsername() %>
            </td>
	        <td align="right" height="19" valign="bottom">
				<a href="cambiarClave.acc" title="Modificar su Clave">
					<img src="/bandejaweb/img/boton_cambioclave.gif" border="0" vspace="0" hspace="0"
						width="15" height="15" title="Modificar su Clave"></a>
				<a href="verBandeja.acc" title="Ir a su Bandeja Inicial">
					<img src="/bandejaweb/img/boton_home.gif" border="0" vspace="0" hspace="0"
						width="15" height="15" title="Ir a su Bandeja Inicial"></a>
				<a href="logoff.acc?exit=true" title="Desconectarse de @Tiempo">
					<img src="/bandejaweb/img/boton_salir.gif" width="15" height="15" border="0"
						title="Desconectarse de @Tiempo"></a>
	     	</td>
        </tr>
      </tbody></table>
    </td>
  </tr>
  <tr> 
    <td height="19" width="18"><img src="/bandejaweb/img/t.gif" height="8" width="8"></td>
    <td rowspan="5" height="100" width="100"><IMG src="/bandejaweb/img/logo.gif" width="100" height="100"></td>

  </tr>
  <tr> 
    <td bgcolor="#def84c" height="19" width="18"><img src="/bandejaweb/img/t.gif" height="19" width="1"></td>
    <td class="fecha" align="right" bgcolor="#def84c" height="19" width="558"><img src="/bandejaweb/img/t.gif" height="19" width="1"> 
      <!--<select name="select" class="telefmundo"><option selected="selected">Mantenedores &nbsp;-&nbsp;Usuarios </option></select>
      <select name="select2" class="telefmundo"><option selected="selected">Bandejas </option></select>-->
    </td>
  </tr>

  <tr> 
    <td width="18"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
    <td width="558"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
  </tr>
  <tr> 
    <td bgcolor="#d0f500" height="9" width="18"><img src="/bandejaweb/img/t.gif" height="9" width="1"></td>
    <td bgcolor="#d0f500" height="9" width="558"><img src="/bandejaweb/img/t.gif" height="9" width="1"></td>
  </tr>
  <tr> 
    <td height="17" width="18"><img src="/bandejaweb/img/t.gif" height="17" width="1"></td>

    <td class="fecha" align="right" height="17" width="558">&nbsp; </td>
  </tr>
  </tbody> 
</table>
<br>
<table class="listado" align="center">
<!--<tr>
	<td>
		<div align="center"> <IMG border="0" src="/bandejaweb/img/ok.gif" width="22" height="20"></div>
	</td>
</tr>-->
<tr class="encabezados-tabla">
	<td><B>Informe de Cambio Clave</b></td>	
</tr>

<tr class="encabezados-tabla">
	<td><%=cambioClave%></td>
</tr>
<tr class="encabezados-tabla">
	<td align="center"><input type="button" value="Reingresar" onclick="jsBandeja()"></td>	
</tr>
</table>
</form>
</BODY>
</HTML>
