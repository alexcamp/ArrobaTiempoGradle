<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>

<% String msj = (String)request.getAttribute("msj");%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Mensaje</TITLE>
<link href="/bandejaweb/estilo.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<table border="0" cellpadding="0" cellspacing="0" height="75" width="780">
  <tbody> 
  <tr align="right"> 
    <td colspan="2"> <img src="/bandejaweb/img/t.gif" height="10" width="1"></td>
    <td rowspan="2" class="fecha" align="right" valign="bottom" width="462"> 
      <table border="0" cellpadding="0" cellspacing="0" width="95%">
        <tbody><tr> 
          <td class="fecha"><img src="/bandejaweb/img/icono_user.gif" height="14" width="14">           
          	<!--
          		TODO:  Sacar el rol y el nombre del usuario que esta utilizando
          		la pantalla.
          	-->
			<!--%=u.getNombreRol()%-->: <!--%=u.getUsername()%-->
		  </td>
          <td align="right"><a href="#" class="enlcabportada"><img src="/bandejaweb/img/b_cambio_clave.gif" alt="Modificar su Clave" title="Modificar su Clave" border="0" height="15" width="15"></a> 
            <a href="#"><img src="/bandejaweb/img/b_home.gif" alt="Ir a su Bandeja Inicial de @Tiempo BA" title="Ir a su Bandeja Inicial de @Tiempo BA" border="0" height="15" width="15"></a> 
            <a href="#"><img src="/bandejaweb/img/b_salir.gif" alt="Desconectarse de @Tiempo BA" title="Desconectarse de @Tiempo BA" border="0" height="15" width="15">

            </a></td>
        </tr>
      </tbody></table>
    </td>
  </tr>
  <tr> 
    <td height="19" width="18"><img src="/bandejaweb/img/t.gif" height="8" width="8"></td>
    <td rowspan="5" height="100" width="100"><img src="/bandejaweb/img/logo.gif" height="100" width="100"></td>

  </tr>
  <tr> 
    <td bgcolor="#def84c" height="19" width="18"><img src="/bandejaweb/img/t.gif" height="19" width="1"></td>
    <td class="fecha" align="right" bgcolor="#def84c" height="19" width="\"><img src="/bandejaweb/img/t.gif" height="19" width="1"> 
      <select name="select" class="telefmundo"><option selected="selected">Mantenedores &nbsp;-&nbsp;Usuarios </option></select>
      <select name="select2" class="telefmundo"><option selected="selected">Bandejas </option></select>
    </td>
  </tr>

  <tr> 
    <td width="18"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
    <td width="370"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
  </tr>
  <tr> 
    <td bgcolor="#d0f500" height="9" width="18"><img src="/bandejaweb/img/t.gif" height="9" width="1"></td>
    <td bgcolor="#d0f500" height="9"><img src="/bandejaweb/img/t.gif" height="9" width="1"></td>
  </tr>
  <tr> 
    <td height="17" width="18"><img src="/bandejaweb/img/t.gif" height="17" width="1"></td>

    <td class="fecha" align="right" height="17">&nbsp; </td>
  </tr>
  </tbody> 
</table>

<table class="listado">
<tr class="encabezados-tabla">
<td width="60"><img src="/bandejaweb/img/error.png"></td>
<td><%=msj%></td>
</tr>
</table>

</BODY>
</HTML>
