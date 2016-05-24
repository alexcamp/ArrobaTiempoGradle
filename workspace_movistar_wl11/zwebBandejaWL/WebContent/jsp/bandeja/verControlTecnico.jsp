<%@page import="co.com.atiempo.dto.DepartamentoDTO"%>
<%@page import="co.com.atiempo.dto.MunicipioDTO"%>
<%@page import="co.com.atiempo.dto.LocalidadDTO"%>
<%@page import="co.com.atiempo.dto.TecnicoDTO"%>
<%@page import="co.com.atiempo.dto.RangoDTO" %>
<%@page import="co.com.atiempo.dto.EmpresaDTO" %>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.RolDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.ControlTecnicoDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.ResultMatrizDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@page import="com.telefonica_chile.bandeja.web.acciones.UtilesBandeja" %>
<%@page import="com.tecnonautica.utiles.tablas.Tabla" %>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig"%>
<%@page import="com.telefonica_chile.atiempo.utiles.Fecha"%>


<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>

<%

UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
Tabla listaTecnicos = (Tabla) request.getAttribute("listaTecnicos");

ArrayList listLocalidad=(ArrayList)request.getAttribute("listLocalidad");
ArrayList listaMunicipio=(ArrayList)request.getAttribute("listaMunicipio");
ArrayList listDepartamento=(ArrayList)request.getAttribute("listaDepartamento");
ArrayList listAplicaciones = (ArrayList) request.getAttribute("listAplicaciones");
ArrayList listRangos = (ArrayList) request.getAttribute("listRangos");
ArrayList listaFiltroTecnicos=(ArrayList)request.getAttribute("listaFiltroTecnicos");
ArrayList listaEmpresa=(ArrayList)request.getAttribute("listaEmpresa");
ResultMatrizDTO resultMatrizDTO=(ResultMatrizDTO)request.getAttribute("resultMatriz");

Fecha fecha=new Fecha();
String fechaHoy="";
if(request.getParameter("fechaCompromiso")!=null)
{
	fechaHoy=request.getParameter("fechaCompromiso");
}
else
{
	fechaHoy=fecha.getFechaconFormato(8);
}
%>
<!-- INICIO FILTRO -->
<SCRIPT LANGUAGE='JavaScript' src='/bandejaweb/js/NumbersOnly.js'></SCRIPT>
<SCRIPT SRC="/bandejaweb/js/selectorFechaHoraNormal.js"></SCRIPT>
<script language="JavaScript">
<!--
function cambiaContratista()
{
	if(document.forma.idContratista.value!="")
	{
		document.forma.codTecnico.value="0";
		document.forma.submit();
	}
}
function filtrarTec()
{
	document.forma.submit();
}
function limpiarFiltro()
{
	document.forma.fechaCompromiso.value=document.forma.fechaInicial.value;
	document.forma.departamento.value="00";
	document.forma.municipio.value="00000";
	document.forma.localidad.value="00000000";
	document.forma.idContratista.value="";
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
		<td class="titulo-tabla">Filtro de Tecnicos</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img
			src="/bandejaweb/img/restaurar.gif" height="14" border="0"> </a></td>
	</tr>
</table>

<form name="forma" action="verControlTecnico.acc" method="post">
<input type="hidden" name="filtroInicial" value="1">
<input type="hidden" name="fechaInicial" value="<%=fecha.getFechaconFormato(8)%>">
	<table id="filtro-grande" width="100%">
	<tr class="titulo-tabla">
		<td colspan="8" class="titulo-tabla">Filtro de Tecnicos</td>
		<td align="right" valign="bottom" width="239"><a
			href="javascript:oculta_filtro();" class="link-simple-filtro"
			title="Ocultar Filtro"> <img src="/bandejaweb/img/minimizar.gif"
			height="14" border="0"> </a></td>
	</tr>
	<tr class="elementos-filtro">
		<td rowspan="3"> </td>
		<td valign="middle">Contratista</td>
		<td valign="middle" align="left">:
			<select name="idContratista" onchange="cambiaContratista();">
			<OPTION value="">--Todos--</OPTION>
			<%
				String valorContra="";
				if(request.getParameter("idContratista")!=null)
					valorContra=request.getParameter("idContratista");
				for(Iterator iterator=listaEmpresa.iterator();iterator.hasNext();)
				{
					EmpresaDTO empresaDTO=(EmpresaDTO)iterator.next();
				%>
				<option <%=UtilesBandeja.selected(empresaDTO.getEmprId().toString(), valorContra)%>
				value="<%=empresaDTO.getEmprId()%>"><%=empresaDTO.getEmpresaNombre()%></option>
				<%
				}
			%>
			</select>
		</td>
		<td valign="middle" width="104">Departamento</td>
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
		<TD align="right" width="54">Ambito:</TD>
		<TD align="right" width="137">
			<select name="aplicacion">
				<%for (Iterator it = listAplicaciones.iterator(); it.hasNext();)
				{
					AplicacionDTO aplicacion = (AplicacionDTO) it.next();
					if(aplicacion.getApId().intValue()==1)
						continue;
					String valorAplicacion = request.getParameter("aplicacion");%>
				<option <%=UtilesBandeja.selected(aplicacion.getApId().toString(), valorAplicacion)%>
					value="<%=aplicacion.getApId()%>"><%=aplicacion.getApNombre()%></option>
				<%}%>
			</select>
		</TD>
			
		<td class="elementos-filtro" rowspan="3" valign="middle" width="36">
		<div align="center"><img src="/bandejaweb/img/separador_vertical.gif"
			height="60" width="2" align="middle"></div>
		</td>

		<td rowspan="3" width="239">
		<div align="center">Mostrar <select name="dpp">
			<%for (int i = 10; i <= 50; i += 10) {
%>
			<option value="<%=i%>"
				<%=String.valueOf(i).equals(request.getParameter("dpp")) ? "selected" : ""%>><%=i%></option>
			<%}
%>
		</select>&nbsp;d.&nbsp;p.&nbsp;pág.<br>
		<br>
			<input type="button" name="botonFiltro" onclick="filtrarTec();" value=" Filtrar " class="botones-chicos"> &nbsp;&nbsp;
			<input type="button" name="botonFiltro" onclick="limpiarFiltro();" value=" Limpiar " class="botones-chicos">
		</div>
		</td>
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Técnico</td>
		<td valign="middle" align="left"> 
		:<SELECT name="codTecnico">
			<OPTION value="0">--Todos--</OPTION>
			<%
				String valorTecnico="";
				if(request.getParameter("codTecnico")!=null)
					valorTecnico=request.getParameter("codTecnico");
				for(Iterator iterator=listaFiltroTecnicos.iterator();iterator.hasNext();)
				{
					TecnicoDTO tecnicoDTO=(TecnicoDTO)iterator.next();
					%>
					<option <%=UtilesBandeja.selected(tecnicoDTO.getCodTecnico(), valorTecnico)%>
					value="<%=tecnicoDTO.getCodTecnico()%>" ><%=tecnicoDTO.getNombreApellido(" ")%></option>
					<%
				}
			%>
		</SELECT>
		</td>
		<td valign="middle" width="104">Municipio</td>
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
		<td class="elementos-filtro" width="54"></td>
		<td class="elementos-filtro" width="137"></td>
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Fecha</td>
		<!--<td colspan="3">:-->
		<td >:<INPUT type="text" name="fechaCompromiso" size="10" readonly="readonly" value='<%=fechaHoy%>' ><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaCompromiso','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"></td>
		<td valign="middle" width="104">Localidad</td>
		<td valign="middle" width="164">:<select name="localidad" onchange="cambioLocalidad();">
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
		<td class="elementos-filtro" width="54"></td>
		<td class="elementos-filtro" width="137"></td>
	</tr>

</table>
<!-- SEPARADOR FILTRO/LISTADO -->
<div style="font-size: 2px">&nbsp;</div>

<!-- BLOQUE LISTADO PETICIONES -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="titulo-tabla">Listado de Trabajos Asignados</td>
	</tr>
</table>

<table class="tabla-borde-delgado" width="100%">
	<tr>
		<td class="elementos-filtro">
		</td>
		<td class="elementos-filtro" align="right">
			<jsp:include page="/jsp/paginador.jsp" flush="true" />
		</td>
	</tr>
</table>
<%if(listaTecnicos!=null && resultMatrizDTO!=null){%>
<%int largoRangos=listRangos.size();%>
<table id="table" class="listado" width="100%">
	<tr class="encabezados-tabla">
		<td nowrap="nowrap">Cod.Tecnico</td>
		<td nowrap="nowrap">Nombre Tecnico</td>
		<%
			for(Iterator iterator=listRangos.iterator();iterator.hasNext();)
			{
				RangoDTO rangoDTO=(RangoDTO)iterator.next();
				%>
				<TD><%=rangoDTO.getHoraDesde()%>-<%=rangoDTO.getHoraHasta()%></TD>
				<%
			}
		%>
		<TD>Total</TD>
	</tr>
	<%
		int k=0;
		for(Iterator iterator=listaTecnicos.iterator();iterator.hasNext();)
		{
			ControlTecnicoDTO controlTecnicoDTO=(ControlTecnicoDTO)iterator.next();	
			%>
			<TR class="fila-detalle-<%=k%2==1 ? "par" : "impar"%>">
			<td nowrap="nowrap">
			<%=controlTecnicoDTO.getCodTecnico()%>&nbsp;</td>
			<td><%=controlTecnicoDTO.getNombre()%>&nbsp;<%=controlTecnicoDTO.getApellido()%>&nbsp;</td>
			<%
			int valorTot=0;
			for(Iterator iterator2=listRangos.iterator();iterator2.hasNext();)
			{
				RangoDTO rangoDTO=(RangoDTO)iterator2.next();
				int valorCuadro=resultMatrizDTO.getValorCodTecnicoEnRango(controlTecnicoDTO.getCodTecnico(),rangoDTO.getIdRango());
				valorTot+=valorCuadro;
				%>
			<td> &nbsp;<%=valorCuadro%>&nbsp;</td>
			<%
			}
			k++;
			%>
			<TD><%=valorTot%></TD>
			</TR>
			<%
		}
	%>
</table>
<%}%>
</form>
<%
	if(request.getParameter("aplicacion")==null)
	{
	%>
	<SCRIPT>
	document.forma.aplicacion.value=3;
	</SCRIPT>
	<%
	}
%>