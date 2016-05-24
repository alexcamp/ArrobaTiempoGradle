<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>Alta-Baja Masiva Modem</TITLE>
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</HEAD>
<BODY>
<form name="forma" action="./operacionMasivaModem.acc">
<br/>
<table class="tabla-borde-delgado" style="width: 800">
	<tr class="titulo-tabla" style="width: 8px">
		<td>Respuesta operaci&oacute;n</td>
	</tr>
	<tr class="elementos-filtro">
		<td><%= request.getAttribute("respuesta")%></td>
	</tr>
	<tr class="elementos-filtro"><td style="height: 8px"></td></tr>
	<tr class="elementos-filtro" style="text-align: right">
		<td style="text-align: center"><input type="button" class="botones-chicos" value="Regresar" onclick="document.forma.submit()"></td>
	</tr>
	<tr class="elementos-filtro"><td style="height: 8px"></td></tr>
</table><br/>
</form>
</BODY>
</HTML>
