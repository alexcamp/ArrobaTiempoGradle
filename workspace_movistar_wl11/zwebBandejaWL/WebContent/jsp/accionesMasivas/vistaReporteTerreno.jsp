<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig"%>
<%@page import="com.telefonica_chile.atiempo.utiles.FileDTO"%>
<%@page import="java.io.File" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.String" %>
<%@page import="com.telefonica_chile.atiempo.utiles.Fecha" %>
<%@page import="com.telefonica_chile.atiempo.utiles.FechaException" %>
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<TITLE>Listado Masivo</TITLE>
<SCRIPT>
	function enviarBandeja()
	{
		document.location.href="iframeReporteTerreno.acc";
	}
	function inicio()
	{
		<%
		if(request.getAttribute("generandoArchivo")!=null)
		{
			%>
			//alert("Generando Archivo");
			setTimeout("enviarBandeja();",8000);
			<%
		}
		if(request.getAttribute("fechasMalas")!=null)
		{
		%>
			alert("El rango de Fechas ingresado no es válido.\nRecuerde ingresar un rango no mayor a tres días");
			return;
			<%
		} 
	%>
	}
</SCRIPT>
</HEAD>

<BODY onload="inicio();">
<%if(request.getAttribute("generandoArchivo")!=null){%>
<IMG src="/bandejaweb/img/relojarena.gif">
<%}else{%>
<table border="1" class="contenido" style="position: absolute;left: 30px;">
<TR>
	<TD colspan="5" class="titulo-tabla">Reporte Terreno</TD>
</TR>
<TR class="titulo-tabla">
		<TD>Nombre de Archivo</TD>
		<TD>Tipo</TD>
		<TD>Fecha</TD>
		<TD>Tamaño</TD>
		<TD></TD>
</TR>
<%
	ArrayList listaArchivos=new ArrayList();
	if(request.getAttribute("URL_ACCION_ARCHIV_TER")!=null)
	{
		String valorRutaMasIsp=(String)request.getAttribute("URL_ACCION_ARCHIV_TER");
		File file=new File(valorRutaMasIsp);
		if(file.isDirectory())
		{
			File[] lista=file.listFiles();
			for(int i=0;i<lista.length;i++)
			{
				File archivo=lista[i];
				if(archivo.isFile())
				{
					if(archivo.length()==0)
						continue;
					FileDTO fileDTO=new FileDTO();
					fileDTO.setFile(archivo);
					listaArchivos.add(fileDTO);
				}
			}
		}
	}
	if(listaArchivos.size()>0)	
		java.util.Collections.sort(listaArchivos);
	int i=0;
	for(java.util.Iterator iterator=listaArchivos.iterator();iterator.hasNext();)
	{
		FileDTO fileDTO=(FileDTO)iterator.next();
		File archivo=fileDTO.getFile();
		i++;
		%>
		<TR  class="fila-detalle-<%=i%2==1 ? "par" : "impar"%>">
			<%
				String tipo="";
				Fecha fecha=null;
				String nombreAr=archivo.getName();
				try
				{
					 nombreAr=nombreAr.substring(nombreAr.lastIndexOf("_")+1,nombreAr.lastIndexOf(".")-1);
					 fecha=new Fecha(nombreAr,"ddMMyyyyHHmmss");
				}
				catch(FechaException ff)
				{
				
				}
				catch(Exception ee)
				{
					
				}
				
				String nombreOri=archivo.getName();
				if(nombreOri.startsWith("ATer"))
					tipo="Archivo Terreno";
				String nombreRutaDescarga=String.valueOf(archivo.getAbsoluteFile());
				long tamano=archivo.length();
				String ta="";
				if(tamano<1024)
					ta=tamano+"Bytes.";
				else if(tamano>1024 && tamano<1048576)
					ta=tamano/1024+"KB";
				else if(tamano>1048576)
					ta=tamano/1048576+"MB";
			%>
			<TD align="left">
			<a href="/bandejaweb/VerArchivo?url=<%= nombreRutaDescarga %>&nameArchi=<%= archivo.getName()%>">
				<%= archivo.getName()%>
			</a>	
			</TD>
			<TD align="left">
			<%=tipo %>
			</TD>
			<TD align="left"><%if(fecha!=null){%><%=fecha.getFechaconFormato() %><%}%></TD>
			<TD><%=ta%></TD>
			<TD align="left">
				<a href="/bandejaweb/VerArchivo?url=<%= nombreRutaDescarga %>&nameArchi=<%= archivo.getName()%>">Descargar</a>
		</TR>
		<%
	}
%>
<TR>
	<TD colspan="5" align="center" class="titulo-tabla">
	<input type="button" onclick="document.location.reload();" value="Refrescar" class="botones-chicos">
	</TD>
</TR>
</table>
<%}%> 
</BODY>
</HTML>
