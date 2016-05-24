<%@page import="com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO" %>
<%@page import="co.com.atiempo.dto.DepartamentoDTO"%>
<%@page import="co.com.atiempo.dto.MunicipioDTO"%>
<%@page import="co.com.atiempo.dto.LocalidadDTO"%>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.RolDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@page import="com.telefonica_chile.bandeja.web.acciones.UtilesBandeja" %>
<%@page import="com.tecnonautica.utiles.tablas.Tabla" %>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig"%>
<%@page import="com.telefonica_chile.atiempo.utiles.Fecha"%>
<%@page import="com.telefonica_chile.bandeja.dto.PeticionDTO" %>

<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>

<%

// adocarmo - CR9664 - inicio
String action = "verBandejaEsperaTerra.acc";
String esAula = "N";
if (request.getParameter("aula")!= null) {
	action = "verBandejaEsperaAula365.acc";
	esAula = "S";
} 
// adocarmo - CR9664 - fin

UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
Tabla listaBackOffice = (Tabla) request.getAttribute("listaBackOffice");

ArrayList listLocalidad=(ArrayList)request.getAttribute("listLocalidad");
ArrayList listaMunicipio=(ArrayList)request.getAttribute("listaMunicipio");
ArrayList listDepartamento=(ArrayList)request.getAttribute("listaDepartamento");

//ArrayList listAplicaciones = (ArrayList) request.getAttribute("listAplicaciones");

ArrayList listSegmentos = (ArrayList) request.getAttribute("listSegmentos");
TreeMap otrostipos = (TreeMap) request.getAttribute("otrostipos");

Fecha fecha=new Fecha();
String fechaIni="";
String fechaFin="";
if(request.getParameter("fechaIni")!=null)
{
	fechaIni=request.getParameter("fechaIni");
}
else
{
	fechaIni=fecha.getFechaconFormato(8);
}
if(request.getParameter("fechaFin")!=null)
{
	fechaFin=request.getParameter("fechaFin");
}
else
{
	fechaFin=fecha.getFechaconFormato(8);
}
%>
<!-- INICIO FILTRO -->
<SCRIPT LANGUAGE='JavaScript' src='/bandejaweb/js/NumbersOnly.js'></SCRIPT>
<SCRIPT SRC="/bandejaweb/js/selectorFechaHoraNormal.js"></SCRIPT>
<script language="JavaScript">
<!--
function verPT(nroPeticion)
{
	document.forma2.idPeticion.value=nroPeticion;
	document.forma2.submit();
}
function limpiar()
{
	var frm=document.formaBuscar;
	frm.submit();
}
function ordenar(valor)
{
	document.forma.orden.value=valor;
	document.forma.submit();
}
function filtrarBACK()
{
	document.forma.submit();
}
function limpiarFiltro()
{
	document.forma.fechaIni.value=document.forma.fechaHoyInicial.value;
	document.forma.fechaFin.value=document.forma.fechaHoyInicial.value;
	document.forma.departamento.value="00";
	document.forma.municipio.value="00000";
	document.forma.localidad.value="00000000";
	document.forma.segmento.value="";
	//document.forma.otrostipos.value="";
	//document.forma.estado.value="0";
	document.forma.familiaPetiAtis.value="00";
	document.forma.orden.value="1";
}

function cambiaUrl(url)
{
	document.location=url;
}
function cambioDepto()
{
	document.forma.municipio.value="00000";
	document.forma.localidad.value="00000000";
	document.forma.submit();
}
function cambioMunicipio()
{
	document.forma.localidad.value="00000000";
	document.forma.submit();
}
function cambioLocalidad()
{
	//document.forma.submit();
}
// -->
</script>
<table id="filtro-chico" style="display: none" width="100%">
	<tr class="titulo-tabla">
		<td class="titulo-tabla">Filtro de Peticiones</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img
			src="/bandejaweb/img/restaurar.gif" height="14" border="0"> </a></td>
	</tr>
</table>

<form name="forma" action="<%=action %>" method="post" id="formaBuscar">
<input type="hidden"  name="orden" value="1">
<input type="hidden"  name="realizarBusqueda" value="1">
<INPUT type="hidden"  name="fechaHoyInicial" value="<%=fecha.getFechaconFormato(8) %>">
	<table id="filtro-grande" width="100%">
	<tr class="titulo-tabla">
		<td colspan="9" class="titulo-tabla">Filtro de Peticiones</td>
		<td align="right" valign="bottom" width="144"><a
			href="javascript:oculta_filtro();" class="link-simple-filtro"
			title="Ocultar Filtro"> <img src="/bandejaweb/img/minimizar.gif"
			height="14" border="0"> </a></td>
	</tr>
	<tr class="elementos-filtro">
		<td rowspan="3"> </td>
		<td valign="middle">Segmento</td>
		<td valign="middle" width="137">
			:&nbsp;<SELECT name="segmento">
			<OPTION value="">Todos los segmentos</OPTION>
			<%
			String valorSegmento = request.getParameter("segmento");
			for (Iterator it = listSegmentos.iterator(); it.hasNext();) {
			SegmentoDTO segmento = (SegmentoDTO) it.next();
	
%>
			<OPTION
				<%=UtilesBandeja.selected(segmento.getSegmId().toString(), valorSegmento)%>
				value="<%=segmento.getSegmId()%>"><%=segmento.getSegmDescripcion()%></OPTION>
			<%}
%>
		</SELECT></td>
		<TD valign="middle" align="left" width="114"></TD>
		<td valign="middle" width="76">Departamento</td>
		<td valign="middle" width="164">:
		<select name="departamento" onchange="cambioDepto();">
			<OPTION value="00" >Seleccione Departamento</OPTION>
			<%
			String valorDep="";
			if(request.getParameter("departamento")!=null)
				valorDep=request.getParameter("departamento");
			for(Iterator iterator=listDepartamento.iterator();iterator.hasNext();)
			{
				DepartamentoDTO departamentoDTO=(DepartamentoDTO)iterator.next();
				%>
				<option <%=UtilesBandeja.selected(departamentoDTO.getCodDpt(), valorDep)%>
				value="<%=departamentoDTO.getCodDpt()%>"><%=departamentoDTO.getNombreDepartamento()%></option>
				<%
			}
			%>
		</select>
		</td>
		<TD valign="middle" >Dias Espera:</TD>
		<TD valign="middle" ><input type="text" maxlength="2" size="3" name="DIFDAYS" value="<%=request.getParameter("DIFDAYS") != null
	? request.getParameter("DIFDAYS")
	: "1"%>"; onkeypress="return numbersonly(this, event)">
			</TD>
			
		<td class="elementos-filtro" rowspan="3" valign="middle" width="7">
		<div align="center"><img src="/bandejaweb/img/separador_vertical.gif"
			height="60" width="2" align="middle"></div>
		</td>

		<td rowspan="3" width="144">
		<div align="center">Mostrar <select name="dpp">
			<%for (int i = 10; i <= 50; i += 10) {
%>
			<option value="<%=i%>"
				<%=String.valueOf(i).equals(request.getParameter("dpp")) ? "selected" : ""%>><%=i%></option>
			<%}
%>
		</select>&nbsp;d.&nbsp;p.&nbsp;pág.<br>
		<br>
			<input type="button" name="botonFiltro" onclick="filtrarBACK();" value=" Filtrar " class="botones-chicos"> &nbsp;&nbsp;
			<input type="button" name="botonFiltro" onclick="limpiarFiltro();" value=" Limpiar " class="botones-chicos">
		</div>
		</td>
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Familia</td>
		<td valign="middle" width="137">
		<%
			String familiaAntes="00";
			if(request.getParameter("familiaPetiAtis")!=null)
				familiaAntes=request.getParameter("familiaPetiAtis");
		%> 
		:&nbsp; <SELECT name="familiaPetiAtis">
				<OPTION value="00" <% if(familiaAntes.equals("00")){ %> selected="selected" <%}%>>Seleccione Familia</OPTION>
				<OPTION value="LB" <% if(familiaAntes.equals("LB")){ %> selected="selected" <%}%>>Solo LB</OPTION>
				<OPTION value="BA" <% if(familiaAntes.equals("BA")){ %> selected="selected" <%}%>>Solo BA</OPTION>
				<OPTION value="TV" <% if(familiaAntes.equals("TV")){ %> selected="selected" <%}%>>Solo TV</OPTION>
				<OPTION value="IC" <% if(familiaAntes.equals("IC")){ %> selected="selected" <%}%>>Solo IC</OPTION>
				<OPTION value="TVIC" <% if(familiaAntes.equals("TVIC")){ %> selected="selected" <%}%>>TV e IC</OPTION>
				<OPTION value="BAIC" <% if(familiaAntes.equals("BAIC")){ %> selected="selected" <%}%>>BA e IC</OPTION>
				<OPTION value="BATV" <% if(familiaAntes.equals("BATV")){ %> selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="BATVIC" <% if(familiaAntes.equals("BATVIC")){ %> selected="selected" <%}%>>BA,TV e IC</OPTION>
				<OPTION value="LBIC" <% if(familiaAntes.equals("LBIC")){ %> selected="selected" <%}%>>LB e IC</OPTION>
				<OPTION value="LBTV" <% if(familiaAntes.equals("LBTV")){ %> selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="LBBA" <% if(familiaAntes.equals("LBBA")){ %> selected="selected" <%}%>>LB y BA</OPTION>
				<OPTION value="LBTVIC" <% if(familiaAntes.equals("LBTVIC")){ %> selected="selected" <%}%>>LB,TV e IC</OPTION>
				<OPTION value="LBBAIC" <% if(familiaAntes.equals("LBBAIC")){ %> selected="selected" <%}%>>LB,BA e IC</OPTION>
				<OPTION value="LBBATV" <% if(familiaAntes.equals("LBBATV")){ %> selected="selected" <%}%>>LB,BA y TV</OPTION>
				<OPTION value="LBBATVIC" <% if(familiaAntes.equals("LBBATVIC")){ %> selected="selected" <%}%>>LB,BA,TV e IC</OPTION>
			</SELECT></td>
		<TD valign="middle" align="left" width="114"></TD>
		<td valign="middle" width="76">Municipio</td>
		<td valign="middle" width="164">:
			<select name="municipio" onchange="cambioMunicipio();">
				<OPTION value="00000">Seleccione Municipio</OPTION>
				<%
				String valorMun="";
				if(request.getParameter("municipio")!=null)
					valorMun=request.getParameter("municipio");
				for(Iterator iterator=listaMunicipio.iterator();iterator.hasNext();)
				{
					MunicipioDTO municipioDTO=(MunicipioDTO)iterator.next();
					%>
					<option <%=UtilesBandeja.selected(municipioDTO.getCod_mun(), valorMun)%>
					value="<%=municipioDTO.getCod_mun()%>" ><%=municipioDTO.getNombre_municipio()%></option>
					<%
				}
				%>
			</select>
		</td>
		<td class="elementos-filtro" width="54" valign="middle"></td>
		<td class="elementos-filtro" valign="middle" width="152"></td>
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Rango Fecha</td>
		<td width="137">:&nbsp; <INPUT type="text" name="fechaIni" size="10" readonly="readonly" value='<%=fechaIni%>' ><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaIni','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"> -</td>
		<TD nowrap="nowrap" width="114"><INPUT type="text" name="fechaFin" size="10" readonly="readonly" value='<%=fechaFin%>' ><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaFin','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"></TD>
		<td valign="middle" width="76">Localidad</td>
		<td valign="middle" width="164">:&nbsp;<select name="localidad" onchange="cambioLocalidad();">
			<option value="00000000">Seleccione Localidad</option>
			<%
			String valorLoc="";
			if(request.getParameter("localidad")!=null)
				valorLoc=request.getParameter("localidad");
			for(Iterator iterator=listLocalidad.iterator();iterator.hasNext();)
			{
				LocalidadDTO localidadDTO=(LocalidadDTO)iterator.next();
				%>
				<OPTION <%=UtilesBandeja.selected(localidadDTO.getCodLoc(), valorLoc)%>
				value="<%=localidadDTO.getCodLoc()%>"><%=localidadDTO.getNombreLocalidad()%></OPTION>
				<%
			}
			%>
		</select>
		</td>
		<td class="elementos-filtro" width="54" valign="middle"></td>
		<td class="elementos-filtro" valign="middle" width="152">
		</td>
	</tr>

</table>
<!-- SEPARADOR FILTRO/LISTADO -->
<div style="font-size: 2px">&nbsp;</div>

<!-- BLOQUE LISTADO PETICIONES -->
<table cellpadding="0" cellspacing="0" border="0" width="100%" id="tabla1">
	<tr>
		<td class="titulo-tabla">Listado de Peticiones</td>
	</tr>
</table>

<table class="tabla-borde-delgado" width="100%" id="tabla2">
	<tr>
		<td class="elementos-filtro">
		</td>
		<td class="elementos-filtro" align="right">
			<jsp:include page="/jsp/paginador.jsp" flush="true" />
		</td>
	</tr>
</table>
<table id="table" class="listado" width="100%">
	<tr class="encabezados-tabla">
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(1)" style="cursor: hand" alt="Orden Ascendentemente">NºAtis<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(2)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(3)" style="cursor: hand" alt="Orden Ascendentemente">NºAtiempo<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(4)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(5)" style="cursor: hand" alt="Orden Ascendentemente">Departamento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(6)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(7)" style="cursor: hand" alt="Orden Ascendentemente">Localidad<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(8)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(9)" style="cursor: hand" alt="Orden Ascendentemente">Segmento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(10)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(11)" style="cursor: hand" alt="Orden Ascendentemente">SubSegmento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(12)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<TD nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(13)" style="cursor: hand" alt="Orden Ascendentemente">Fecha Inicio<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(14)" style="cursor: hand" alt="Orden Descendentemente"></TD>
		<TD nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(15)" style="cursor: hand" alt="Orden Ascendentemente">Fecha Fin<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(16)" style="cursor: hand" alt="Orden Descendentemente"></TD>
	</tr>
	<%
	if(listaBackOffice!=null)
	{
		int k=0;	
		for(Iterator iterator=listaBackOffice.iterator();iterator.hasNext();)
		{
			PeticionDTO peticionDTO=(PeticionDTO)iterator.next();
			%>
			<TR class="fila-detalle-<%=k%2==1 ? "par" : "impar"%>">
			<td nowrap="nowrap">
				<%=peticionDTO.getCod_pet_cd()%>
			</td>
			<td nowrap="nowrap">
				<A href="javascript:verPT(<%=peticionDTO.getBiNroPeticion()%>)" >
				<%=peticionDTO.getBiNroPeticion()%>
				</A>
			</td>
			<td nowrap="nowrap"><%=peticionDTO.getDepartamento()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getLocalidad()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getSegmentoDescripcion()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getSubSegmentoDescripcion()%></td>
		<TD nowrap="nowrap"><%=peticionDTO.getFechaInicioS()%></TD>
		<TD nowrap="nowrap"><%=peticionDTO.getFechaFinS()%></TD>
	</TR>
			<%
			k++;
		}
	}
	%>
</table>

</form>
<form name="forma2" action="verPTVPI.acc" method="post" target="ventanaNueva">
<input type="hidden" name="idPeticion" id="idPeticion" value="0">

<input type="hidden" name="aula" value="<%=esAula%>">

</form>