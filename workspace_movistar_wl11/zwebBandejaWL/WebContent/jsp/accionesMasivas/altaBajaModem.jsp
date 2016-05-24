<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<TITLE>Alta-Baja Masiva Modem</TITLE>
<script type="text/javascript" language="javascript">
function validar(){
	var retorno = document.forma.file.value!="";
	if(!retorno){alert('Debe elegir un archivo a cargar...')}
	retorno = retorno && confirm('Desea realmente ejecutar una '+document.forma.operacion.value + ' masiva con el archivo seleccionado?');
	if(retorno)document.forma.submit();
}
</script>
</HEAD>
<BODY>
<form name="forma" id="forma" action="./operacionMasivaModem.acc"
	enctype="multipart/form-data" method="post">
<br/>
<table class="tabla-borde-delgado" style="width: 800">
	<tr class="titulo-tabla"><td colspan="4">Alta-Baja Modem</td></tr>
	<tr class="elementos-filtro">
		<td style="height: 8px" colspan="4"></td>
	</tr>
	<tr>
		<td class="elementos-filtro">Tipo operaci&oacute;n masiva</td>
		<td class="elementos-filtro"><select name="operacion" id="operacion">
			<option value="Alta">Alta</option>
			<option value="Baja">Baja</option>
		</select></td>
		<td class="elementos-filtro">Archivo de entrada</td>
		<td class="elementos-filtro"><input type="file" name="file" id="file"></td>
	</tr>
	<tr class="elementos-filtro"><td style="height: 8px" colspan="4"></td></tr>
	<tr class="elementos-filtro">
		<td colspan="4" style="text-align: center">
			<input type="button" name="aceptar" value="Aceptar" onclick="validar()" class="botones-chicos">
		</td>
	</tr>
	<tr class="elementos-filtro"><td style="height: 8px" colspan="4"></td></tr>
</table>
<br/>
</form>
</BODY>
</HTML>
