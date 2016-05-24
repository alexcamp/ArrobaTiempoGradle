
<%@page import="com.telefonica_chile.bandeja.web.acciones.UtilesBandeja" %>
<%@ page import = "com.telefonica_chile.bandeja.torreControl.DatosGlobalTorreControl" %>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig" %>
<HTML>
<HEAD>
<script>
function verLista(agencia, actividad)
{
	//window.parent.seteaParametros(agencia, actividad);
	
}
</script>

<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Torre de Control</TITLE>
</HEAD>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">

<BODY>
<table id="filtro-chico" style="display: none" width="100%">
	<tr class="titulo-tabla">
		<td class="titulo-tabla">Filtro de Bandeja de Entrada</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img src="/bandejaweb/img/restaurar.gif"
			height="14" border="0"> </a></td>
	</tr>
</table>
<%String error = (String) request.getAttribute("error");
if (error != null) {
	out.write(error);
} else {
	DatosGlobalTorreControl d =
		(DatosGlobalTorreControl) request.getAttribute("datos");
	Object datos[][] = d.datos;
	
    Long idAplicacion=new Long(-1);
	String idAplicacionSTR=(String) request.getAttribute("aplicacion");
    if((idAplicacionSTR!=null)&&(!idAplicacionSTR.trim().equals("")))
	   idAplicacion=new Long(idAplicacionSTR);
	
	   
%>


<!-- INICIO LISTADO -->
<table class="tabla-borde-delgado" width="100%">
	<%for (int par = 0, i = 1; i < datos.length; i++, par++) {
		if (i == 0) {%>
		<tr class="encabezados-tabla">
			<%} else {%>
		<tr valign="top"
			class="fila-detalle-<%=par % 2 == 0 ? "par" : "impar"%>">
			<%}%>
			<%for (int j = 0; j < datos[0].length; j++) {
		if (datos[i][j] == null) {%>
			<TD style="width: 2cm;"><%="0"%></TD>
			<%} else if (datos[i][j] instanceof String) {%>
			<TD style="width: 2cm;"><%=datos[i][j]%></TD>
			<%} else if (((DatosGlobalTorreControl) datos[i][j]).cant <= 0) {%>
			<TD style="width: 2cm;"><%="0"%></TD>
			<%} else if ((!idAplicacion.equals(new Long(-1)))&&idAplicacion.equals(ApplicationConfig.APP_ATST_ID)){%>
			<TD style="width: 2cm;">
				<%=((DatosGlobalTorreControl) datos[i][j]).cant%></TD>
			<%} else {%>
			<TD style="width: 2cm;">
			<%--<script>
			<%String url = "";%>
			if (window.parent.document.forma.tipotrabajo.selectedIndex =! 0)
			{
				<%url = "&tipo_trabajo="+(((DatosGlobalTorreControl) datos[i][j]).tipo_trabajo).toString();%>
				
			}
			if (window.parent.document.forma.segmento[window.parent.document.forma.segmento.selectedIndex].value!= "")
			{
				<%url += "&segmento="+((DatosGlobalTorreControl) datos[i][j]).segmento;%>
			}
			
			</script>
			href='verTorreControl.acc?agencia="<%=((DatosGlobalTorreControl) datos[i][j]).agen_id%>"&actividad="<%=((DatosGlobalTorreControl) datos[i][j]).act_id%>"<%=url%>'
			--%>
			<a
				href='javascript:verLista(<%=((DatosGlobalTorreControl)datos[i][j]).agen_id%>,<%=((DatosGlobalTorreControl)datos[i][j]).act_id%>)'
				target="_top"> <%=((DatosGlobalTorreControl) datos[i][j]).cant%></a></TD>
			<%}%>	
			<%}%>
		</tr>
		<%}%>	
</table>
<%}%></BODY>

</HTML>
