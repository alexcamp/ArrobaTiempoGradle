<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>
<%
HashMap mapAplicaciones = (HashMap) request.getAttribute("mapAplicaciones");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Cargar archivo</TITLE>
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</HEAD>
<BODY>
<TABLE class="elementos-filtro" width="300" height="170">
	<tr><TD class="titulo-tabla">Cargar archivo con peticiones a cerrar actividad</TD></tr>
	<tr><TD><br></TD></tr>
	<tr><TD><DIV align="center">
	<!--<FORM name="form1" enctype="multipart/form-data" action="UploadFile.acc" method="Post">-->
	<FORM name="form1" enctype="multipart/form-data" action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/TerminaActividadByFile" method="Post">
		<TABLE>
			<tr><TD>
			<INPUT type="File" name="archivo" class="input-form">
			</TD></tr>
			<tr><TD><br></TD></tr>
			<tr><TD>
			<!--<INPUT type="submit" name="send" value="Enviar" onclick="javascript:alerta();">-->
			<INPUT type="button" name="send" value="Enviar" class="botones-chicos" onclick="javascript:alerta();">
			</TD></tr>
		</TABLE>
	</FORM>
	</DIV></TD></tr>
</TABLE>
<script>
 function alerta(){
 	var res = confirm("Esta seguro de ejecutar la acción masiva para las peticiones que se encuentran el el archivo?");
 	if(res == true){
 		document.form1.target="_blank";
 		//windi = window.open('_blank','Hi','width=200, height=300');
 		//document.form1.target=windi;
		document.form1.submit();
		this.close();
 	}else
 		this.close();
 }
 </script>
</BODY>
</HTML>
