<%@page import="com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO" %>
<%@page import="com.telefonica_chile.atiempo.utiles.STConfig"%>
<%@page import="co.com.atiempo.dto.DepartamentoDTO"%>
<%@page import="co.com.atiempo.dto.MunicipioDTO"%>
<%@page import="co.com.atiempo.dto.LocalidadDTO"%>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.RolDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.AgenciaDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.IspDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.PeticionDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.CampoDTO" %>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.ValorVariableDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.UserCoDto"%>
<%@page import="com.telefonica_chile.bandeja.web.acciones.UtilesBandeja" %>
<%@page import="com.tecnonautica.utiles.basicos.FechaUtil" %>
<%@page import="com.tecnonautica.utiles.tablas.Tabla" %>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.bandeja.dto.AccionMasivaDTO"%>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig"%>
<%@page import="com.telefonica_chile.comun.parametro.session.ParametroDTO"%>

<!--CR 24378-->
<%@page import="co.com.atiempo.dto.Grupos_especialesDTO"%>
<%@page import="co.com.atiempo.dto.CanalDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.CodigoStDTO"%>
<%@page import="co.com.telefonica.atiempo.ejb.eb.CanalLocal"%>
<!--Fin CR 24378-->

<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>

<%
/* Utilizado en la bandeja push */
String redirectBandejaPushURL =
	(String) request.getAttribute("redirectBandejaPush");
if (redirectBandejaPushURL != null) {
	out.println(
		"<html><head><META HTTP-EQUIV=Refresh CONTENT=\"0; URL="
			+ redirectBandejaPushURL
			+ "\"></head></html>");
	return;
}
%>

<%

UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
Tabla peticiones = (Tabla) request.getAttribute("peticiones");
ArrayList listLocalidad=(ArrayList)request.getAttribute("listLocalidad");
ArrayList listaMunicipio=(ArrayList)request.getAttribute("listaMunicipio");
ArrayList listDepartamento=(ArrayList)request.getAttribute("listaDepartamento");
ArrayList listSegmentos = (ArrayList) request.getAttribute("listSegmentos");
ArrayList listaUsuariosQueSuperviso=(ArrayList)request.getAttribute("listaUsuariosQueSuperviso");
ArrayList listRolesUsuario = (ArrayList) request.getAttribute("listRolesUsuario");
ArrayList listAplicaciones = (ArrayList) request.getAttribute("listAplicaciones");
ArrayList listAcciones = (ArrayList) request.getAttribute("listAcciones");
HashMap mapAplicaciones = (HashMap) request.getAttribute("mapAplicaciones");
String tipoOrden = (String)request.getAttribute("tipoOrden")==null?"":(String)request.getAttribute("tipoOrden");
String campoOrden = (String)request.getAttribute("campoOrden")==null?"":(String)request.getAttribute("campoOrden");;

//CR 24378
ArrayList listGrupos_Especiales = (ArrayList) request.getAttribute("listGrupos_Especiales");
ArrayList listCategoriaAveria = (ArrayList) request.getAttribute("listCategoriaAveria");
ArrayList listPrioridadAveria = (ArrayList) request.getAttribute("listPrioridadAveria");
ArrayList listCanales = (ArrayList) request.getAttribute("listCanales");

//Fin CR 24378

//Parametros de segundos para el bloqueo del boton Accion Masiva... 
ParametroDTO parameDto = (ParametroDTO) request.getAttribute("segundos");

//UtilesBandeja utiles = new UtilesBandeja();
String listaPet = (String) request.getAttribute("listaPet");
TreeMap otrostipos = (TreeMap) request.getAttribute("otrostipos");
%>
<!-- INICIO FILTRO -->
<SCRIPT LANGUAGE='JavaScript' src='/bandejaweb/js/NumbersOnly.js'>
</SCRIPT>
<script language="JavaScript">
<!--
	var max_size = 18;
	var min_size = 6;
	var tamagnoLetras = 100;
	function dzIncreaseFontSize(idElemento) {
		var p = document.getElementsByTagName('p');
		for(i=0;i<p.length;i++)
		{
			if(p[i].style.fontSize) {
					var s = parseInt(p[i].style.fontSize.replace("px",""));
			}
			else {
				var s = 11;
			}
			if(s!=max_size) {
	         s += 1;
			}
	      	p[i].style.fontSize = s+"px"
   		}
		
		var q = document.getElementsByTagName('td');
		for(i=0;i<q.length;i++)
		{
			if(q[i].style.fontSize) {
					var s = parseInt(q[i].style.fontSize.replace("px",""));
			}
			else {
				var s = 11;
			}
			if(s!=max_size) {
	         s += 1;
			}
	      	q[i].style.fontSize = s+"px"
   		}
	}
	
	function dzDecreaseFontSize(idElemento) {
		
   		
   		
   		var p = document.getElementsByTagName('p');
		for(i=0;i<p.length;i++) {
			if(p[i].style.fontSize) {
				var s = parseInt(p[i].style.fontSize.replace("px",""));
			}
			else {
				var s = 9;
			}
			if(s!=min_size) {
	         s -= 1;
			}
			p[i].style.fontSize = s+"px"
   		}
   		
   		var q = document.getElementsByTagName('TD');
		for(i=0;i<q.length;i++) {
			if(q[i].style.fontSize) {
				var s = parseInt(q[i].style.fontSize.replace("px",""));
			}
			else {
				var s = 9;
			}
			if(s!=min_size) {
	         s -= 1;
			}
			q[i].style.fontSize = s+"px"
   		}
	}

	var checkpeticiones = new Array();
function quitarCero( str ) {
	var i = -1;
	while ( i < str.length ) {
		i++;
		if ( str.charAt(i) == '0' )
			continue;
		return str.substring(i, str.length);
	}
	return "";
}

function validaFiltro(forma) {
	
	var frm=document.forma;
	for (var i=0;i<frm.elements.length;i++)
	{
		var e = frm.elements[i];
		if(e.type==null || e.type!='text')
			continue;
		e.value=Trim(e.value);
	}
	var miInteger1 = forma.numPeticionaATIS.value;
	var miInteger2 = forma.CCN.value;
	if ( isNaN(miInteger1) )
	{
		alert ("Ingrese sólo números para Número de Petición");
		return;
	}
	if ( isNaN(miInteger2) )
	{
		alert ("Ingrese sólo números para la Central");
		return;
	}
	resetPagina();
	forma.submit();
}
/* Habilitacion de Orden en Bandeja 08112006 - GBA*/
function ordenaBandeja(campo) {
	var tipoOrden = forma.tipoOrden.value;
	var campoOrden = forma.campoOrden.value;
	
	if (campoOrden == campo) {
		if (tipoOrden == "ASC")
			tipoOrden = "DESC";
		else
			tipoOrden = "ASC";
	} else {
		tipoOrden = "ASC";
	}	
	forma.campoOrden.value = campo;
	forma.tipoOrden.value = tipoOrden;
	
	forma.submit();
}

function limpiarFiltro(forma) {
	forma.numPeticionaATIS.value = '';
	forma.rutCliente.value = '';
	forma.dvCliente.value = '';
	forma.departamento.selectedIndex = 0;
	//forma.areaFono.value = '';
	//forma.numFono.value = '';
	forma.rol.selectedIndex = 0;
	forma.familiaPetiAtis.selectedIndex = 0;
	forma.segmento.selectedIndex = 0;
	forma.otrostipos.value = '';
	forma.CCN.value = '';
	// CR 24378
	forma.tipoIncidencia.selectedIndex = 0;
	forma.estadoAgendamiento.selectedIndex = 0;
	forma.categoriaAveria.selectedIndex = 0;
	forma.prioridadAveria.selectedIndex = 0;
	forma.gEspecial.selectedIndex = 0;
	forma.canal.selectedIndex = 0;
	//Fin CR 24378 
	
	forma.submit();
}

function ChequearTodos(val){
 	
 	for (var i=0;i <checkpeticiones.length;i++){
 		eval("document.forma.checkbox"+checkpeticiones[i]).checked = val;
 	}
}


function cambiaUrl(url) {
	document.location=url;
}
function ordenar(valor)
{
	document.forma.ordenaBandeja.value=valor;
	validaFiltro(document.forma);
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
		<td class="titulo-tabla">Filtro de Bandeja de Entrada</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img
			src="/bandejaweb/img/restaurar.gif" height="14" border="0"> </a></td>
	</tr>
</table>
<form name="formaMasiva" method="post">

</form>
<form name="forma" action="peticionesSupervisado.acc" method="post" >
<%
 String idSupervisor="";
 String idSupervisado="";
 String usuasupervisadologin="";
 if(request.getParameter("usua_id")!=null)
 	idSupervisor=request.getParameter("usua_id");
 if(request.getParameter("usua_supervisado")!=null)
 	idSupervisado=request.getParameter("usua_supervisado");
 if(request.getParameter("usuasupervisadologin")!=null)
 	usuasupervisadologin=request.getParameter("usuasupervisadologin");
%>
<input type="hidden" name="ordenaBandeja" value="0">
<input type="hidden" name="usua_id" value="<%=idSupervisor%>">
<input type="hidden" name="usua_supervisado" value="<%=idSupervisado%>">
<input type="hidden" name="usuasupervisadologin" value="<%= usuasupervisadologin %>">
<input type="hidden" name="usuario2" value="<%=usuario.getId()%>"> <input
	type="hidden" name="rolUsr">
	<input type="hidden" name="campoOrden" value="<%= campoOrden %>"> <input type="hidden" name="tipoOrden" value="<%= tipoOrden %>"><input type="hidden" name="usuario"
	value="<%=usuario.getId()%>"> <input type="hidden" name="codigo_subida"
	value="1"> <input type="hidden" name="formato" value="0"> <input
	type="hidden" name="listaPet" value="<%=listaPet%>">

<input type="hidden" name="accion_reasignacion" value="0">
<TABLE width="100%">
	<TR>
		<TD align="right">
		<a href="#" onclick="javascript:dzIncreaseFontSize('table'); return false;"><img src="/bandejaweb/img/ic_AMas_on.gif" width="19" height="17" hspace="1" border="0" alt="Aumentar Fuente" title="Aumentar Fuente"></a>
		<a href="#" onclick="javascript:dzDecreaseFontSize('table'); return false;"><img src="/bandejaweb/img/ic_AMns_on.gif" width="19" height="17" hspace="1" border="0" alt="Disminuir Fuente" title="Disminuir Fuente"></a>
		</TD>
	</TR>
</TABLE>
<table id="filtro-grande" width="100%">
	<tr class="titulo-tabla">
		<td colspan="8" class="titulo-tabla">Filtro de Bandeja de Entrada</td>
		<td align="right" valign="bottom"><a
			href="javascript:oculta_filtro();" class="link-simple-filtro"
			title="Ocultar Filtro"> <img src="/bandejaweb/img/minimizar.gif"
			height="14" border="0"> </a></td>
	</tr>
	<tr class="elementos-filtro">
		<td rowspan="3"> </td>
		<td valign="middle">N° Petición ATIS</td>
		<td valign="middle" align="left">:<input type="text" name="numPeticionaATIS" class="input-form"
			size="18" maxlength="15"
			value="<%=request.getParameter("numPeticionaATIS") != null
	? request.getParameter("numPeticionaATIS")
	: ""%>" onkeypress="return numbersonly(this, event)">
		</td>
		<td valign="middle">Identificación Cliente</td>
		<td valign="middle">: <input type="text" name="rutCliente" class="input-form"
			size="8" maxlength="12"
			value="<%=request.getParameter("rutCliente") != null ? request.getParameter("rutCliente") : ""%>">
		 -  <input type="text" name="dvCliente" class="input-form" size="1"
			maxlength="2"
			value="<%=request.getParameter("dvCliente") != null ? request.getParameter("dvCliente") : ""%>">
		</td>
		<td valign="middle">Departamento</td>
		<td valign="middle">:
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

		<td width="6" class="elementos-filtro" rowspan="3" valign="middle">
		<div align="center"><img src="/bandejaweb/img/separador_vertical.gif"
			height="60" width="2" align="middle"></div>
		</td>

		<td rowspan="3">
		<div align="center">Mostrar <select name="dpp">
			<%for (int i = 10; i <= 50; i += 10) {
%>
			<option value="<%=i%>"
				<%=String.valueOf(i).equals(request.getParameter("dpp")) ? "selected" : ""%>><%=i%></option>
			<%}
%>
		</select>&nbsp;d.&nbsp;p.&nbsp;pág.<br>
		<br>
		<input type="button" name="botonFiltro"
			onclick="return validaFiltro(this.form)" value=" Filtrar "
			class="botones-chicos"> &nbsp;&nbsp;<input type="button"
			name="botonFiltro" onclick="return limpiarFiltro(this.form)"
			value=" Limpiar " class="botones-chicos"></div>

		</td>
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Segmento</td>
		<td valign="middle" align="left"> 
		:<SELECT name="segmento">
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
		<td valign="middle">Rol</td>
		<td valign="middle">: <select name="rol" class="input-form">
			<option value="">Todos mis Roles</option>
			<%for (Iterator it = listRolesUsuario.iterator(); it.hasNext();) {
	RolDTO rol = (RolDTO) it.next();
	String valorRol = request.getParameter("rol");
	if(rol.getId().intValue()==1)
		continue;
%>			
			<option <%=UtilesBandeja.selected(rol.getId().toString(), valorRol)%>
				value="<%=rol.getId()%>"><%=rol.getNombre()%></option>


			<%}
%>
		</select></td>
		<td valign="middle">Municipio</td>
		<td valign="middle">:<select name="municipio" onchange="cambioMunicipio();">
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
		<!-- <td class="elementos-filtro" valign="bottom">...</td> -->
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Central</td>
		<!--<td colspan="3">:-->
		<td >:<INPUT type="text" name="CCN" size="5" maxlength="14"
			value='<%=request.getParameter("CCN") != null ? request.getParameter("CCN") : ""%>' onkeypress="return numbersonly(this, event)"></td>
		<TD valign="middle">Familia</td>
		<TD valign="middle">:
			<%
			String familiaAntes="00";
			if(request.getParameter("familiaPetiAtis")!=null)
				familiaAntes=request.getParameter("familiaPetiAtis");
		%> 
			<SELECT name="familiaPetiAtis">
				<OPTION value="00" <% if(familiaAntes.equals("00")){ %> selected="selected" <%}%>>Seleccione Familia</OPTION>
				<OPTION value="LB" <% if(familiaAntes.equals("LB")){ %> selected="selected" <%}%>>Solo LB</OPTION>
				<OPTION value="BA" <% if(familiaAntes.equals("BA")){ %> selected="selected" <%}%>>Solo BA</OPTION>
				<OPTION value="TV" <% if(familiaAntes.equals("TV")){ %> selected="selected" <%}%>>Solo TV</OPTION>
				<OPTION value="IC" <% if(familiaAntes.equals("IC")){ %> selected="selected" <%}%>>Solo IC</OPTION>
				<OPTION value="TVIC" <% if(familiaAntes.equals("TVIC")){ %> selected="selected" <%}%>>TV e IC</OPTION>
				<OPTION value="BAIC" <% if(familiaAntes.equals("BAIC")){ %> selected="selected" <%}%>>BA e IC</OPTION>
				<OPTION value="BATV" <% if(familiaAntes.equals("BATV")){ %> selected="selected" <%}%>>BA y TV</OPTION>
				<OPTION value="BATVIC" <% if(familiaAntes.equals("BATVIC")){ %> selected="selected" <%}%>>BA,TV e IC</OPTION>
				<OPTION value="LBIC" <% if(familiaAntes.equals("LBIC")){ %> selected="selected" <%}%>>LB e IC</OPTION>
				<OPTION value="LBTV" <% if(familiaAntes.equals("LBTV")){ %> selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="LBBA" <% if(familiaAntes.equals("LBBA")){ %> selected="selected" <%}%>>LB y BA</OPTION>
				<OPTION value="LBTVIC" <% if(familiaAntes.equals("LBTVIC")){ %> selected="selected" <%}%>>LB,TV e IC</OPTION>
				<OPTION value="LBBAIC" <% if(familiaAntes.equals("LBBAIC")){ %> selected="selected" <%}%>>LB,BA e IC</OPTION>
				<OPTION value="LBBATV" <% if(familiaAntes.equals("LBBATV")){ %> selected="selected" <%}%>>LB,BA y TV</OPTION>
				<OPTION value="LBBATVIC" <% if(familiaAntes.equals("LBBATVIC")){ %> selected="selected" <%}%>>LB,BA,TV e IC</OPTION>
			</SELECT>
		</td>
		<td valign="middle">Localidad</td>
		<td valign="middle">:<select name="localidad" onchange="cambioLocalidad();">
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
		</select></td>
	</tr>
	<tr class="elementos-filtro">
		<td>&nbsp;</td>
		<td>Operacion Comercial</td>
		<td> :
			<%
			String opcocat="0";
			if(request.getParameter("opcocat")!=null)
				opcocat=request.getParameter("opcocat");
			%>
			<select name="opcocat">
				<OPTION value="0" <% if(opcocat.equals("0")){ %> selected="selected" <%}%>>Seleccione Tipo Op</OPTION>
				<OPTION value="A" <% if(opcocat.equals("A")){ %> selected="selected" <%}%>>Alta</OPTION>
				<OPTION value="T" <% if(opcocat.equals("T")){ %> selected="selected" <%}%>>Transferencia</OPTION>
				<OPTION value="R" <% if(opcocat.equals("R")){ %> selected="selected" <%}%>>Reconexión</OPTION>
				<OPTION value="S" <% if(opcocat.equals("S")){ %> selected="selected" <%}%>>Suspensión</OPTION>
				<OPTION value="P" <% if(opcocat.equals("P")){ %> selected="selected" <%}%>>PostVenta</OPTION>
			</select></td>
		<td>&Aacute;mbito</td>
		<td>: <select name="aplicacion">
			<OPTION value="">- Seleccione &aacute;mbito</OPTION>
			<%for (Iterator it = listAplicaciones.iterator(); it.hasNext();)
			{
				AplicacionDTO aplicacion = (AplicacionDTO) it.next();
				if(aplicacion.getApId().intValue()==1)
					continue;
				String valorAplicacion = request.getParameter("aplicacion");%>
			<option
				<%=UtilesBandeja.selected(aplicacion.getApId().toString(), valorAplicacion)%>
				value="<%=aplicacion.getApId()%>"><%=aplicacion.getApNombre()%></option>
			<%}%>
		</select></td>
		<td valign="middle">Flujo</td>
		<td valign="middle">: <select name="otrostipos">
			<option value="">Todas</option>
			<%Iterator e = otrostipos.keySet().iterator();
while (e.hasNext()) {
	String nombre = (String) e.next();
	String valorTipo = request.getParameter("otrostipos");%>
			<option
				<%=UtilesBandeja.selected(otrostipos.get(nombre).toString(), valorTipo)%>
				value="<%=otrostipos.get(nombre).toString()%>"><%=nombre%></option>

			<%}%>
		</select></td>
		<td colspan=2>&nbsp;</td>
	</tr>

	<tr class="elementos-filtro">
		<td>&nbsp;</td>
		<td>Categor&iacute;a de Aver&iacute;a</td>
			<td>: <select name="categoriaAveria" id="categoriaAveria">
					<%
					String categoriaAveria ="";
					if(request.getParameter("categoriaAveria")!=null) categoriaAveria = request.getParameter("categoriaAveria");%>
					<OPTION value="" <% if(categoriaAveria.equals("")){ %> selected="selected" <%}%> >-Seleccione Categoria-</OPTION>
					<%for (Iterator iter = listCategoriaAveria.iterator();iter.hasNext();){
						CodigoStDTO codigoSt = (CodigoStDTO)iter.next();
						String codigoCategoria = codigoSt.getCodigo().trim();
						String descripcionCategoria = codigoSt.getDescripcion();%>
						<OPTION value="<%=codigoCategoria%>" <%if(categoriaAveria.trim().equals(codigoCategoria)){ %> selected="selected" <%}%>><%=descripcionCategoria%></OPTION>
					<%}%>
			</select>
		</td>
<!--CR 24378		-->
		<td>Prioridad de aver&iacute;a</td>
		<td>: <select name="prioridadAveria" id="prioridadAveria">
				<%
				String prioridadAveria ="";
				if(request.getParameter("prioridadAveria")!=null) prioridadAveria = request.getParameter("prioridadAveria");%>
				<OPTION value="" <% if(prioridadAveria.equals("")){ %> selected="selected" <%}%> >-Seleccione Prioridad-</OPTION>
				<%for (Iterator iter = listPrioridadAveria.iterator();iter.hasNext();){
					CodigoStDTO codigoSt = (CodigoStDTO)iter.next();
					String codigoPrioridad = codigoSt.getCodigo().trim();
					String descripcionPioridad = codigoSt.getDescripcion();%>
					<OPTION value="<%=codigoPrioridad%>" <%if(prioridadAveria.trim().equals(codigoPrioridad)){ %> selected="selected" <%}%>><%=descripcionPioridad%></OPTION>
				<%}%>
			</select>
		<td valign="middle">Estado de Agendamiento</td>
		<td valign="middle">: <select name="estadoAgendamiento" id="estadoAgendamiento">
		<% 		String estadoAgendamiento ="";
				if(request.getParameter("estadoAgendamiento")!=null) estadoAgendamiento = request.getParameter("estadoAgendamiento");
				%>
			<option value="" 		   <% if(estadoAgendamiento.equals("")){ %> selected="selected" <%}%>>seleccione estado</option>
			<option value="agendado"   <% if(estadoAgendamiento.equals("agendado")){ %> selected="selected" <%}%>>Agendado</option>
			<option value="noAgendado" <% if(estadoAgendamiento.equals("noAgendado")){ %> selected="selected" <%}%>>No agendado</option>
							</select></td>
		<td colspan=2>&nbsp;</td>
	</tr>
	<tr class="elementos-filtro">
		<td>&nbsp;</td>
		<td>Tipo de Incidencia</td>
		<td>: <select name="tipoIncidencia" id="tipoIncidencia">
		<% 		String tipoIncidencia ="";
				if(request.getParameter("tipoIncidencia")!=null) tipoIncidencia = request.getParameter("tipoIncidencia");
				%>
				<OPTION value=""  <% if(tipoIncidencia.equals("")){ %> selected="selected" <%}%>>Seleccione Tipo Incidencia</OPTION>
				<OPTION value="M" <% if(tipoIncidencia.equals("M")){ %> selected="selected" <%}%> >Masiva</OPTION>
				<OPTION value="I" <% if(tipoIncidencia.equals("I")){ %> selected="selected" <%}%>>Individual</OPTION>
				<OPTION value="CA" <% if(tipoIncidencia.equals("CA")){ %> selected="selected" <%}%> >Causas Ajenas</OPTION>
			</select>
		</td>
		<td>Grupos Especiales</td>
		<td>: <select name="gEspecial">
			<OPTION value="">- Seleccione Grupo</OPTION>			
			<% for (Iterator it = listGrupos_Especiales.iterator(); it.hasNext();) {
				Grupos_especialesDTO gEspecial = (Grupos_especialesDTO) it.next();
				String valorGrupo = request.getParameter("gEspecial");
//				if(gEspecial.getTipo().equalsIgnoreCase("Bandeja")){%>
			<option
				<%=UtilesBandeja.selected(gEspecial.getId().toString(), valorGrupo)%>
					value="<%=gEspecial.getId().toString()%>"><%=gEspecial.getDescripcion()%></option>
			<%
//			}
			}
			%>
		</select></td>

		<td >Canal</td>
		<TD>: <select name="canal">
			<OPTION value="">- Seleccione Canal</OPTION>			
			<%for (Iterator it = listCanales.iterator(); it.hasNext();)
			{
				CanalDTO canal = (CanalDTO) it.next();
				String valorCanal = request.getParameter("canal");%>
			<option
				<%=UtilesBandeja.selected(canal.getCodCanal().toString(), valorCanal)%>
				value="<%=canal.getCodCanal()%>"><%=canal.getDescCanal()%></option>
			<%}%>
		</select></TD>
		<!-- Fin CR16847 -->
		<td colspan=2>&nbsp;</td>
	</tr>
<!--Fin CR 24378		-->

</table>
<!-- SEPARADOR FILTRO/LISTADO -->
<div style="font-size: 2px">&nbsp;</div>

<!-- BLOQUE LISTADO PETICIONES -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="titulo-tabla">Listado de Trabajos Asignados</td>
	</tr>
</table>

<!-- *************** ACCIONES MASIVAS ************************************************************ -->
<table class="tabla-borde-delgado" width="100%">
	<tr>
		<td class="elementos-filtro"><select name="accion_masiva"
			class="botones-chicos">
			<option value="" selected>Seleccione una acción Masiva</option>
			<%if (listAcciones != null) {
	for (Iterator it = listAcciones.iterator(); it.hasNext();) {
		AccionMasivaDTO accion = (AccionMasivaDTO) it.next();%>
			<option value="<%=accion.getAcamValor()%>"><%=accion.getAcmaDescripcion()%></option>
			<%}%>
			<%}%> 
		</select> <input type="button"
			onclick="javascript:accionMasiva(this.form, document.getElementById('table'));"
			name="btnimprimir" value="Realizar Acción Masiva" id="btnimprimir"
			class="botones-chicos"><!-- IFRAME DE IMPRESION MASIVA --> <iframe
			src="/bandejaweb/blank.html" name="iprint" id="iprint"
			style="width: 1px; height: 1px; border: 0px"></iframe></td>
		<td class="elementos-filtro" align="right"><jsp:include
			page="/jsp/paginador.jsp" flush="true" /></td>
	</tr>
</table>

<!-- INICIO LISTADO -->
<table id="table" class="tabla-borde-delgadoFchica" width="100%">
	<tr class="encabezados-tabla">
		<td><input type="checkbox" id="checkAll" name="checkAll"
			value="checkAll" onClick='ChequearTodos(this.checked)'></td>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(17)" style="cursor: hand" alt="Orden Ascendentemente">N° Petici&oacute;n<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(18)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(19)" style="cursor: hand" alt="Orden Ascendentemente">Localidad<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(20)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<TD ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(21)" style="cursor: hand" alt="Orden Ascendentemente">Central<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(22)" style="cursor: hand" alt="Orden Descendentemente"></TD>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(23)" style="cursor: hand" alt="Orden Ascendentemente">Actividad<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(24)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(25)" style="cursor: hand" alt="Orden Ascendentemente">Cliente<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(26)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<TD ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(27)" style="cursor: hand" alt="Orden Ascendentemente">Identificación<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(28)" style="cursor: hand" alt="Orden Descendentemente"></TD>
		<TD ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(29)" style="cursor: hand" alt="Orden Ascendentemente">Familia<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(30)" style="cursor: hand" alt="Orden Descendentemente"></TD>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(31)" style="cursor: hand" alt="Orden Ascendentemente">Segmento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(32)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<TD ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(33)" style="cursor: hand" alt="Orden Ascendentemente">SubSegmento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(34)" style="cursor: hand" alt="Orden Descendentemente"></TD>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(35)" style="cursor: hand" alt="Orden Ascendentemente">Fecha Inicio<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(36)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(37)" style="cursor: hand" alt="Orden Ascendentemente">Fecha Apertura<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(38)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td ><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(39)" style="cursor: hand" alt="Orden Ascendentemente">Fecha Compromiso<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(40)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td>Estado Compromiso</td>
		<td>Estado Actividad</td>
	</tr>
	<%int par = 0;
String url = "";
String urlBase = "";

String nameAct = "";
String urlIni = "";
String urlFin = "";
int estPet = 0, tipoT = 0;
String adicionalUrl=null;
String hostnameWasSt="";
String portNumberWasSt="";
String TRABAJA_ST_TORRE="N";
if(STConfig.getVariable("hostnameWasSt")!=null)
	hostnameWasSt=STConfig.getVariable("hostnameWasSt");
if(STConfig.getVariable("portNumberWasSt")!=null)
	portNumberWasSt=STConfig.getVariable("portNumberWasSt");
if(STConfig.getVariable("TRABAJA_ST_TORRE")!=null)
	TRABAJA_ST_TORRE=STConfig.getVariable("TRABAJA_ST_TORRE");
for (Iterator it = peticiones.iterator(); it.hasNext(); par++) {
    adicionalUrl="";
    urlBase = "";
	PeticionDTO peticion = (PeticionDTO) it.next();
	AplicacionDTO aplicacion =
		(AplicacionDTO) mapAplicaciones.get(peticion.getAplicacionNombre());

	if (aplicacion != null && aplicacion.getApUrlBase() != null) {
		// nota: se agregó esto para el caso de que la URL de detalle venga completamente definida (no URL relativas)
		if (!peticion
			.getBiUrlDetalle()
			.trim()
			.toLowerCase()
			.startsWith("http://"))
			urlBase = aplicacion.getApUrlBase();
	}

	url =
		urlBase + peticion.getBiUrlDetalle() + "&ID_USUARIO=" + usuario.getId();
	estPet =
		(peticion.getEstadoPeticion() != null)
			? peticion.getEstadoPeticion().intValue()
			: -1;
	tipoT =
		(peticion.getTipoTrabajo() != null)
			? peticion.getTipoTrabajo().intValue()
			: -1;
	nameAct = peticion.getActividadDescripcion();

	nameAct = (nameAct == null) ? "" : nameAct;
	if (nameAct.startsWith("Pendiente en Legacy")) {
		urlIni = "";
		urlFin = "";
	} else
	{
		adicionalUrl="&usua_supervisado="+idSupervisado+"&usuasupervisadologin="+usuasupervisadologin;
		urlIni = "<a href=\"" + url+ adicionalUrl+ "\">";
		urlFin = "</a>";
	}
%>
	<script language="JavaScript">
		 checkpeticiones[<%=par%>]=<%=peticion.getBiId()%>;
	</script>
	
	<input type="hidden" name="par_<%=peticion.getBiId()%>"
		value="<%=peticion.getBiNroPeticion()%>">
	<input type="hidden" name="petiAtis_<%=peticion.getBiId()%>"
		value="<%=peticion.getCod_pet_cd()%>">		
	<input type="hidden" name="peti<%=par%>"
		value="<%=peticion.getBiNroPeticion()%>">

	<tr valign="top"
		class="fila-detalle-<%=par % 2 == 0 ? "par" : "impar"%>">
		<td nowrap="nowrap"><input type="checkbox" id="checkbox<%=par%>"
			name="checkbox<%=peticion.getBiId()%>"
			value="<%=peticion.getBiId()%>"></td>
		<%
		if(peticion.getApId()!=null && peticion.getApId().longValue()==3)
		{
		%>
			<td nowrap="nowrap"><%=urlIni%>
			<%if(peticion.getCod_pet_cd()!=null){%><%=peticion.getCod_pet_cd()%>&nbsp;-&nbsp;<%}%>
			<%if(peticion.getAgrupaciones()!=null){%>(<%=peticion.getAgrupaciones()%>)&nbsp;-&nbsp;<%}%>
			<%=peticion.getBiNroPeticion()%><%=urlFin%>
			<input type="hidden" name="num<%=par%>" value="<%=peticion.getBiNroPeticion()%>">
			<input type="hidden" name="url_<%=peticion.getBiId()%>" value="<%=url%>">
			<input type="hidden" name="actPet_<%=peticion.getBiId()%>" value="<%=peticion.getActividadCodigo()%>">
			</td>
		<%
		}
		else if(peticion.getApId()!=null && peticion.getApId().longValue()==2 && TRABAJA_ST_TORRE.equals("S"))
		{
		%>
			<td nowrap="nowrap"><%=urlIni%>
			<%if(peticion.getCod_pet_cd()!=null){%><%=peticion.getCod_pet_cd()%>&nbsp;-&nbsp;<%}%>
			<%if(peticion.getAgrupaciones()!=null){%>(<%=peticion.getAgrupaciones()%>)&nbsp;-&nbsp;<%}%>
			<%=peticion.getBiNroPeticion()%><%=urlFin%>
			<input type="hidden" name="num<%=par%>" value="<%=peticion.getBiNroPeticion()%>">
			<input type="hidden" name="url_<%=peticion.getBiId()%>" value="<%=url%>">
			<input type="hidden" name="actPet_<%=peticion.getBiId()%>" value="<%=peticion.getActividadCodigo()%>">
			</td>
		<%}
		else if(peticion.getApId()!=null && peticion.getApId().longValue()==2 && TRABAJA_ST_TORRE.equals("N"))
		{
			if(peticion.getCod_pet_cd()!=null)
			{
				String urlVS="http://"+hostnameWasSt+":"+portNumberWasSt+"/soltecweb/BuscadorPeticionSTCoAcc.acc?folio="+peticion.getCod_pet_cd()+"&busca_opcion=1";
			%>
		
			<td nowrap="nowrap">
				<a href="javascript:verVentana('<%=urlVS %>');"><%=peticion.getCod_pet_cd()%></a>
				&nbsp;-&nbsp;
				<a href="javascript:verVentana('<%=urlVS %>');"><%=peticion.getBiNroPeticion()%></a>
				<input type="hidden" name="num<%=par%>" value="<%=peticion.getBiNroPeticion()%>">
				<input type="hidden" name="url_<%=peticion.getBiId()%>" value="<%=url%>">
				<input type="hidden" name="actPet_<%=peticion.getBiId()%>" value="<%=peticion.getActividadCodigo()%>">
			</td>
			<%
			}
		}
		%>
		
		<td><%if(peticion.getLocalidad()!=null){%><%=peticion.getLocalidad()%><%}%></td>
		<TD><%if(peticion.getCentral()!=null){%><%=peticion.getCentral()%><%}%></TD>
		<td><%=nameAct%></td>
		<td><%=urlIni%><%=peticion.getBiClienteNombre() != null ? peticion.getBiClienteNombre() : ""%><%=urlFin%></td>
		<TD><%if(peticion.getBiClienteRut()!=null){%><%=peticion.getBiClienteRut()%><%}%><%if(!peticion.getBiClienteRutDv().equals("")){%>-<%=peticion.getBiClienteRutDv()%><%}%></TD>
		<TD><%=peticion.getBiFamiliaPsDes()%></TD>
		<td><%if(peticion.getSegmentoDescripcion()!=null){%><%=peticion.getSegmentoDescripcion()%><%}%></td>
		<TD><%if(peticion.getSubSegmentoDescripcion()!=null){%><%=peticion.getSubSegmentoDescripcion()%><%}%></TD>
		<td><%=UtilesBandeja.formatoFecha(peticion.getBiFechaInicio(), "dd/MM/yyyy HH:mm", "")%></td>
		<td><%=UtilesBandeja.formatoFecha(peticion.getBiFechaApertura(), "dd/MM/yyyy HH:mm", "")%></td>
		<td><%=UtilesBandeja.formatoFecha(peticion.getBiFechaCompromiso(), "dd/MM/yyyy HH:mm", "")%></td>
		<td><img height="12"
			src="img/<%=UtilesBandeja.decideColorSemaforo(peticion.getBiSemaforoCompromiso())%>"></td>
		<td><img height="12"
			src="img/<%=UtilesBandeja.decideColorSemaforo(peticion.getBiSemaforoActividad())%>"></td>
	</tr>
	<%}
	%>
	<TR>
		<TD colspan="15" align="right" class="elementos-filtro">
			Reasignar Peticiones Marcadas a Usuario:<SELECT name="idnuevoUsuario">
			<OPTION value="">--Seleccione Usuario--</OPTION>
			<%
			for(Iterator iterator=listaUsuariosQueSuperviso.iterator();iterator.hasNext();)
			{
				UserCoDto dto=(UserCoDto)iterator.next();
				if(dto.getUsuaId()!=null && dto.getUsuaId().toString().equals(idSupervisado))
					continue;
				%>
				<option value="<%=dto.getUsuaId()%>" ><%=dto.getUsuaLogin()%></option>
				<%
			}
			%>
			</SELECT>
			<input type="button" name="btnReasignaPetCo" onclick="reasignarPeticiones();" value=" Reasignar " class="botones-chicos">
		</TD>
	</TR>
	<TR>
		<TD colspan="15" align="right" class="elementos-filtro">
			<input type="button" name="btnVolverbSupe" value=" Volver a Bandeja Supervisor" class="botones-chicos" onclick="verHomeSupervisor();">
		</TD>
	</TR>
	<%
if (peticiones.size() == 0) {

%>
	<tr valign="top" class="fila-detalle-par">
		<td colspan="15">No se encontraron
		items</td>
	</tr>
	<%}%>
</table>
<!-- FIN LISTADO --></form>

<script>
 function accionMasiva(forma, tabla){

   // restauro valores iniciales del formulario
   
   //document.forma.action = "AccionMasiva.acc";
   //document.forma.target = "";
   
   var opcion  = document.forma.accion_masiva[document.forma.accion_masiva.selectedIndex].value;
   
   if (opcion=='imprimir')
   {
  	  blockButton();
   	  imprimir(forma);
	 
   }  
   
   if (opcion=='archivoCentrales')
   {
  	  blockButton();
   	  archivoCentrales(forma);
	 
   }
   
   if (opcion=='archivoODS')
   {
  	  blockButton();
   	  archivoODS(forma);
	 
   }
   
   if (opcion=='archivoODS_ST')
   {
  	  blockButton();
   	  archivoODS_ST(forma);
   }

  /* if ( (opcion=='imprimir') || (opcion=='terminar') ){*/
  if (opcion=='terminar') {
  	 blockButtonF();
	 jsTerminarTrabajo(forma);
   }

   if (opcion=='cancelar') {
	 blockButton();
	 jsCancelarTrabajo(forma);
   }

   if (opcion=='resumen') {
	 jsEjecutarResumenSSVA(tabla);

   }

   if (opcion=='comando') {
  	 jsEjecutarComandosSSVA(tabla);
 
   }

   if(opcion=='enviar_ssva'){
   	blockButton();
   	jsEnviarPeticionesSSVA(forma);
   }
   
   if(opcion=='bintegrada'){
   blockButton();
   jsBintegrada(tabla);
   }

   }
 
 function jsBintegrada(table){
	var rolId  = document.forma.rol[document.forma.rol.selectedIndex].value;
    document.forma.rolUsr.value=rolId;
    
 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("BANDEJA")).getApUrlBase()%>/GenerarBintegrada";
 	document.forma.submit();
 	
    //document.forma.action="verBandeja.acc";
 }

function abrirVentana(url, target, ancho, alto) {
	var params = "width=" + ancho + ",height=" + alto + ",resizable=auto,top=0,left=0";
	var newWin = window.open(url, target, params);
	newWin.focus();
}

 function archivoCentrales(forma)
 {	
	if(!confirm("Esta accion generará el Archivo de Centrales Completo para el usuario. ¿Desea Continuar?\nAdvertencia: Si ya generó el archivo CANCELE y busque en la Bandeja de Reportes"))
 	{
	 	document.forma.action="verBandeja.acc";
	 	document.forma.target = "";
	 	return;
 	}
	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/GeneraArchivo?iduserAccion=<%=idSupervisado %>&userAccion=<%=usuasupervisadologin %>";
 	document.forma.submit();

 	document.forma.action="verBandeja.acc";
 	document.forma.target = "";
 	
 	//Leo
 	//formaMasiva
 	setTimeout("enviarBandejaMasiva();",15000);
 	
 }
 
 	function enviarBandejaMasiva()
	{
		var formaMasiva = document.formaMasiva;
		var actionOld = formaMasiva.action;
		var newWin = window.open("about:blank",'PantallaMasiva','width=800,height=600,resizable=yes,top=0,left=0,scrollbars=yes');
		formaMasiva.action = "/bandejaweb/ListadoMasivo.acc";
		formaMasiva.target = "PantallaMasiva";
		formaMasiva.submit();
		formaMasiva.action = actionOld;
		newWin.focus();
	}
 
 function archivoODS(forma)
 {
	if(!confirm("Esta accion generará el Archivo de ODS Completo para el usuario. ¿Desea Continuar?\nAdvertencia: Si ya generó el archivo CANCELE y busque en la Bandeja de Reportes"))
 	{
	 	document.forma.action="verBandeja.acc";
	 	document.forma.target = "";
	 	return;
 	}
 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/GeneraAODS?iduserAccion=<%=idSupervisado%>&userAccion=<%=usuasupervisadologin%>";
 	document.forma.submit();

 	document.forma.action="verBandeja.acc";
 	document.forma.target = "";
 	
 	setTimeout("enviarBandejaMasiva();",30000);
 }
 
 function archivoResignacion(forma)
 {
	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/ReasignaPet?userAccion=<%=usuario.getUsername()%>";
 	document.forma.submit();

 	document.forma.action="peticionesSupervisado.acc";
 	document.forma.target = "";
 	
 	setTimeout("cambio();",5000);
 }
 
 function archivoODS_ST(forma)
 {
 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("ATST")).getApUrlBase()%>/GeneraAODS?userAccion=<%=usuario.getUsername()%>";
 	document.forma.submit();

 	document.forma.action="peticionesSupervisado.acc";
 	document.forma.target = "";
 }
 
 function jsEjecutarResumenSSVA(table) {
	var rolId  = document.forma.rol[document.forma.rol.selectedIndex].value;
    document.forma.rolUsr.value=rolId;
    document.forma.formato.value="1";
    if ( !forma.checkAll.checked ) {
    	blockButton();
	 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/FormatoArchivo";
	 	
	} else {
		abrirVentana("about:blank", "ArchivoSSVA", 600, 400);
	 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("BANDEJA")).getApUrlBase()%>/ListadoArchivoSSVA.acc";
	 	document.forma.target="ArchivoSSVA";
	 	
	}
	
 	document.forma.submit();
 	document.forma.action="verBandeja.acc";
 	document.forma.target = "";
 	
 }
 
 function jsEjecutarComandosSSVA(table) {
	
	var rolId  = document.forma.rol[document.forma.rol.selectedIndex].value;
    document.forma.rolUsr.value=rolId;
    document.forma.formato.value="2";
    if ( !forma.checkAll.checked ) {
    	blockButton();
	 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/FormatoArchivo";
	 	
	} else {
		abrirVentana("about:blank", "ArchivoSSVA", 600, 400);
	 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("BANDEJA")).getApUrlBase()%>/ListadoArchivoSSVA.acc";
	 	document.forma.target="ArchivoSSVA";

	}

 	document.forma.submit();
    document.forma.action="verBandeja.acc";
 	document.forma.target = "";

 }
 
 function imprimir(forma){
   document.forma.action="AccionMasiva.acc";
   document.forma.target="iprint";
   document.forma.submit();
   document.forma.action = "verBandeja.acc";
   document.forma.target = "";
 }
 
 
 function jsTerminarTrabajo(forma){
 	document.forma.action="<%=((AplicacionDTO) mapAplicaciones.get("VPISTBBA")).getApUrlBase()%>/TerminaActividad?userAccion=<%=usuario.getUsername()%>";
 	document.forma.target="iprint";
 	document.forma.submit();

 	document.forma.action="verBandeja.acc";
 	document.forma.target = "";
 	setTimeout("cambio();",<%if (parameDto.getValorParametro() == null) { %> 42000 <%  }else%><%=new Integer(parameDto.getValorParametro()).intValue()  - new Integer("18000").intValue()%>);
 
/*	document.forma.action= "ProcesoBatch.acc";
	document.forma.target="iprint";
	document.forma.submit();
	document.forma.action = "verBandeja.acc";
	document.forma.target = "";*/
 }
 
 function cambio()
 {
		validaFiltro(document.forma);
 }
 
 function jsCancelarTrabajo(forma){
	document.forma.action= "ProcesoBatch.acc"; 
	document.forma.target="iprint";
	document.forma.submit();
	document.forma.action = "verBandeja.acc";
	document.forma.target = "";
 } 
 

  function jsEnviarPeticionesSSVA(forma){
//  document.forma.action="ProcesoBatch.acc";
//  document.forma.target="iprint";
    document.forma.action = "verBandeja.acc";
    document.forma.target = "";
    document.forma.submit();
   
  }
  function verHomeSupervisor()
  {
  	document.forma.action = "verBandejaSupCo.acc";
    document.forma.target = "";
    document.forma.submit();
    
  }
  
</script>

<SCRIPT>

function blockButton(){
    var boton = document.getElementById('btnimprimir');
    
    boton.value = '     Cargando...      ';
    boton.disabled="disabled";
	setTimeout ('activeButton()', <%if (parameDto.getValorParametro() == null) { %> 60000 <% }else%><%=new Integer(parameDto.getValorParametro())%>);
	
}
function activeButton(){
    var boton = document.getElementById('btnimprimir');
    
    boton.value = 'Realizar Acción Masiva';
   	boton.disabled=false;

}

function blockButtonF(){
    var boton = document.getElementById('btnimprimir');
    var botonF = document.getElementById('botonFiltro');
    
    boton.value = '     Cargando...      ';
   // botonF.value = 'Cargando...';
    boton.disabled="disabled";
    botonF.disabled="disabled";
	setTimeout ('activeButtonF()', <%if (parameDto.getValorParametro() == null) { %> 60000 <% } else%><%=new Integer(parameDto.getValorParametro())%>);
	
}
function activeButtonF(){
    var boton = document.getElementById('btnimprimir');
    var botonF = document.getElementById('botonFiltro');
    
    boton.value = 'Realizar Acción Masiva';
    //botonF.value = ' Filtrar ';
   	boton.disabled=false;
   	botonF.disabled=false;

}
function reasignarPeticiones()
{
	if(document.forma.idnuevoUsuario.value=="")
	{
		alert("Debe seleccionar un usuario a quien reasignarle la peticion");
		return;
	}
	blockButton();
   	archivoResignacion(forma);
}
function verVentana(url)
{
	window.open(url,"PeticionesBus",'width=800,height=600,scrollbars=yes,resizable=yes,top=0,left=0');
}
</SCRIPT>

