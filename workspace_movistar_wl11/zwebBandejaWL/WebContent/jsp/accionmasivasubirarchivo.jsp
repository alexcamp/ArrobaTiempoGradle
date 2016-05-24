<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@include file="template/head.jsp"%>
<%     
  String titulo = "Subida de Archivo version 2.0";
%>
<script language="JavaScript" type="text/JavaScript">		
	
	function Validar(archivo){
		
			if(archivo==""){
			   alert("Debe Elegir un Archivo");
			}  
			else{ 
				document.frm.submit(); 
			}
	}		
		
	
				
</script>
<%
	String usuario2 =   (String)request.getAttribute("usuario2");
%>
<!--
< %@include file="commons/header.jspf"% >
< %@include file="commons/headerATiempo.jspf"% >
-->
<%
	String urlAccionMasiva = "/bandejaweb/AccionMasivaSubirArchivoSSVA";
%>
	<br><br><br>
	<form name="frm" action="<%=urlAccionMasiva%>" method="POST" ENCTYPE="multipart/form-data">
	<input type="hidden" name="usuario" value="<%=usuario.getId()%>">
	
	<table width="30%" align="center">
		<tr>
			<TD><input type="file" name="archivo"></TD>
		</tr>	
	</table>	
	<table width="30%" align="center">
		<tr>
			<TD align="right"><input type="button" value="Upload" onclick="Validar(document.frm.archivo.value)"></TD>

		</tr>
	</table>
	</form>
	
	 
	
</BODY>
</HTML>
