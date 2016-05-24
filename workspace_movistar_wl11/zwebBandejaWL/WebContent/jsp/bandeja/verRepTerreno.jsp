<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>
<%@page import="com.telefonica_chile.atiempo.utiles.Fecha"%>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%
	Fecha fecha=new Fecha();
	Fecha fecha2=new Fecha();
	fecha2.addDays(-3);
	UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
%>
<!-- INICIO FILTRO -->
<SCRIPT>
	function resetar()
	{
		var f1=document.getElementById("fechaIni");
		var f2=document.getElementById("fechaFin");
		var fi=document.getElementById("fechaHoyInicial");
		
		f1.value=fi.value;
		f2.value=fi.value;
		
		document.iframeReporte.location.href="iframeReporteTerreno.acc";
	}
	function generarArchi()
	{
		var f1=document.getElementById("fechaIni");
		var f2=document.getElementById("fechaFin");
		var tipo=document.getElementById("opcocat");
		/*if(tipo.value=='0')
		{
			alert("Debe ingresar el tipo de Operacion Comercial");
			return;
		}*/
		document.iframeReporte.location.href="iframeReporteTerreno.acc?f1="+f1.value+"&f2="+f2.value+"&opcocat="+tipo.value+"&usuaId=<%=usuario.getId() %>&userAccion=<%=usuario.getUsername() %>";
	}
</SCRIPT>
<SCRIPT SRC="/bandejaweb/js/selectorFechaHoraNormal.js"></SCRIPT>

<table id="filtro-chico" style="display: none" width="100%">
	<tr class="titulo-tabla">
		<td class="titulo-tabla">Filtro de Archivo</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img
			src="/bandejaweb/img/restaurar.gif" height="14" border="0"> </a></td>
	</tr>
</table>

<form name="forma" action="verBandejaBackOC.acc" method="post">
<input type="hidden"  name="realizarBusqueda" value="1">
<INPUT type="hidden"  name="fechaHoyInicial" value="<%=fecha.getFechaconFormato(8)  %>" id="fechaHoyInicial">
	<table id="filtro-grande" width="100%">
	<tr class="titulo-tabla">
		<td colspan="10" class="titulo-tabla">Filtro de Archivo</td>
		<td align="right" valign="bottom" width="132"><a
			href="javascript:oculta_filtro();" class="link-simple-filtro"
			title="Ocultar Filtro"> <img src="/bandejaweb/img/minimizar.gif"
			height="14" border="0"> </a></td>
	</tr>
	<tr class="elementos-filtro">
	<%
		String feIni=null;
		String feFin=null;
		if(request.getParameter("fechaIni")!=null)
			feIni=request.getParameter("fechaIni");
		else
			feIni=fecha2.getFechaconFormato(8);
		
		if(request.getParameter("fechaFin")!=null)
			feFin=request.getParameter("fechaFin");
		else
			feFin=fecha.getFechaconFormato(8);
	%>
		<td valign="middle" colspan="2" nowrap="nowrap">Fecha Inicio:<INPUT type="text" name="fechaIni" id="fechaIni" size="10" readonly="readonly" value='<%=feIni %>' ><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaIni','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"></td>
		<td valign="middle" colspan="2" nowrap="nowrap">Fecha Termino:<INPUT type="text" name="fechaFin" id="fechaFin" size="10" readonly="readonly" value='<%=feFin %>' ><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaFin','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"></td>
		<TD valign="middle" colspan="3" nowrap="nowrap">Op. Com:<%
			String opcocat="0";
			if(request.getParameter("opcocat")!=null)
				opcocat=request.getParameter("opcocat");
			%><select name="opcocat" id="opcocat">
				<OPTION value="0" <% if(opcocat.equals("0")){ %> selected="selected" <%}%>>Seleccione Tipo Op</OPTION>
				<OPTION value="A" <% if(opcocat.equals("A")){ %> selected="selected" <%}%>>Alta</OPTION>
				<OPTION value="T" <% if(opcocat.equals("T")){ %> selected="selected" <%}%>>Transferencia</OPTION>
				<OPTION value="R" <% if(opcocat.equals("R")){ %> selected="selected" <%}%>>Reconexión</OPTION>
				<OPTION value="S" <% if(opcocat.equals("S")){ %> selected="selected" <%}%>>Suspensión</OPTION>
				<OPTION value="P" <% if(opcocat.equals("P")){ %> selected="selected" <%}%>>PostVenta</OPTION>
			</select></TD>
		<td valign="middle">
		
		<!-- se comenta la opcion del boton para Generacion Archivo - Ana Santos - Inicio -->		
		<!--<input type="button" value="Generar Archivo" class="botones-chicos" onclick="generarArchi();">--> 		 
		<!-- se comenta la opcion del boton para Generacion Archivo - Ana Santos - Fin -->	
		
		</td>
		<td valign="middle"><input type="button" value="Reset" class="botones-chicos" onclick="resetar();">   </td>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
	</tr>

</table>

<div style="font-size: 2px">&nbsp;</div>


<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="titulo-tabla">Listado de Archivos</td>
	</tr>
</table>
<iframe name="iframeReporte" id="iframeReporte" height="320" src="iframeReporteTerreno.acc" frameborder="0" scrolling="yes" width="822px;">
</iframe>
</form>