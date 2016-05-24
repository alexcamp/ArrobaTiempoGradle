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
<%@page import="com.telefonica_chile.comun.parametro.session.ParametroDTO"%>

<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>

<%

UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
Tabla listaBackOffice = (Tabla) request.getAttribute("listaBackOffice");

ArrayList listLocalidad=(ArrayList)request.getAttribute("listLocalidad");
ArrayList listaMunicipio=(ArrayList)request.getAttribute("listaMunicipio");
ArrayList listDepartamento=(ArrayList)request.getAttribute("listaDepartamento");
ArrayList listAplicaciones = (ArrayList) request.getAttribute("listAplicaciones");
ArrayList listSegmentos = (ArrayList) request.getAttribute("listSegmentos");
TreeMap otrostipos = (TreeMap) request.getAttribute("otrostipos");

//Parametros de segundos para el bloqueo del boton Renvio Masivo... 
ParametroDTO parameDto = (ParametroDTO) request.getAttribute("segundos");

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
	forma.otrostipos.value = '';
}


var checkpeticiones = new Array();

function ChequearTodos(val){
 	
 	for (var i=0;i <checkpeticiones.length;i++){
 		eval("document.forma.checkbox"+checkpeticiones[i]).checked = val;
 	}
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
		<td class="titulo-tabla">Filtro de Peticiones Sigres</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img
			src="/bandejaweb/img/restaurar.gif" height="14" border="0"> </a></td>
	</tr>
</table>

<form name="forma" action="verBandejaEsperaSigres.acc" method="post">
<input type="hidden"  name="orden" value="1">
<input type="hidden"  name="realizarBusqueda" value="1">
<INPUT type="hidden"  name="fechaHoyInicial" value="<%=fecha.getFechaconFormato(8) %>">
<TABLE id="filtro-grande" width="100%">
	<TBODY>
		<TR class="titulo-tabla">
			<TD colspan="9" class="titulo-tabla">Filtro de Peticiones Sigres</TD>
			<TD valign="middle" align="left" width="8"></TD>
			<TD align="right" valign="bottom" width="167"><A
				href="javascript:oculta_filtro();" class="link-simple-filtro"
				title="Ocultar Filtro"><IMG src="/bandejaweb/img/minimizar.gif"
				height="14" border="0"></A></TD>
		</TR>
		<TR class="elementos-filtro">
			<TD rowspan="3"> </TD>
			<TD valign="middle">Segmento</TD>
			<TD valign="middle" width="119"><SELECT name="segmento">
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
			</SELECT></TD>
			<TD valign="middle" align="left" width="73"></TD>
			<TD valign="middle" width="151">Departamento</TD>
			<TD valign="middle" width="164"><SELECT name="departamento"
				onchange="cambioDepto();">
				<OPTION value="00">Seleccione Departamento</OPTION>
				<%
			String valorDep="";
			if(request.getParameter("departamento")!=null)
				valorDep=request.getParameter("departamento");
			for(Iterator iterator=listDepartamento.iterator();iterator.hasNext();)
			{
				DepartamentoDTO departamentoDTO=(DepartamentoDTO)iterator.next();
				%>
				<OPTION
					<%=UtilesBandeja.selected(departamentoDTO.getCodDpt(), valorDep)%>
					value="<%=departamentoDTO.getCodDpt()%>"><%=departamentoDTO.getNombreDepartamento()%></OPTION>
				<%
			}
			%>
			</SELECT></TD>
			<TD valign="middle" align="left" width="73"></TD>
			<TD valign="middle" width="54"></TD>
			<TD valign="middle" width="172"></TD>

			<TD class="elementos-filtro" rowspan="3" valign="middle" width="8">
			<DIV align="center"><IMG src="/bandejaweb/img/separador_vertical.gif"
				height="97" width="2" align="middle"></DIV>
			</TD>

			<TD rowspan="3" width="167">
			<DIV align="center">Mostrar <SELECT name="dpp">
				<%for (int i = 10; i <= 50; i += 10) {
%>
				<OPTION value="<%=i%>"
					<%=String.valueOf(i).equals(request.getParameter("dpp")) ? "selected" : ""%>><%=i%></OPTION>
				<%}
%>
			</SELECT>&nbsp;d.&nbsp;p.&nbsp;pág.<BR>
			<BR>
			<INPUT type="button" name="botonFiltro" onclick="filtrarBACK();"
				value=" Filtrar " class="botones-chicos"> &nbsp;&nbsp; <INPUT
				type="button" name="botonFiltro" onclick="limpiarFiltro();"
				value=" Limpiar " class="botones-chicos"></DIV>
			</TD>
		</TR>

		<TR class="elementos-filtro">
			<TD valign="middle">Familia</TD>
			<TD valign="middle" width="119"><%
			String familiaAntes="00";
			if(request.getParameter("familiaPetiAtis")!=null)
				familiaAntes=request.getParameter("familiaPetiAtis");
		%> <SELECT name="familiaPetiAtis">
				<OPTION value="00" <% if(familiaAntes.equals("00")){ %>
					selected="selected" <%}%>>Seleccione Familia</OPTION>
				<OPTION value="LB" <% if(familiaAntes.equals("LB")){ %>
					selected="selected" <%}%>>Solo LB</OPTION>
				<OPTION value="BA" <% if(familiaAntes.equals("BA")){ %>
					selected="selected" <%}%>>Solo BA</OPTION>
				<OPTION value="TV" <% if(familiaAntes.equals("TV")){ %>
					selected="selected" <%}%>>Solo TV</OPTION>
				<OPTION value="IC" <% if(familiaAntes.equals("IC")){ %>
					selected="selected" <%}%>>Solo IC</OPTION>
				<OPTION value="TVIC" <% if(familiaAntes.equals("TVIC")){ %>
					selected="selected" <%}%>>TV e IC</OPTION>
				<OPTION value="BAIC" <% if(familiaAntes.equals("BAIC")){ %>
					selected="selected" <%}%>>BA e IC</OPTION>
				<OPTION value="BATV" <% if(familiaAntes.equals("BATV")){ %>
					selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="BATVIC" <% if(familiaAntes.equals("BATVIC")){ %>
					selected="selected" <%}%>>BA,TV e IC</OPTION>
				<OPTION value="LBIC" <% if(familiaAntes.equals("LBIC")){ %>
					selected="selected" <%}%>>LB e IC</OPTION>
				<OPTION value="LBTV" <% if(familiaAntes.equals("LBTV")){ %>
					selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="LBBA" <% if(familiaAntes.equals("LBBA")){ %>
					selected="selected" <%}%>>LB y BA</OPTION>
				<OPTION value="LBTVIC" <% if(familiaAntes.equals("LBTVIC")){ %>
					selected="selected" <%}%>>LB,TV e IC</OPTION>
				<OPTION value="LBBAIC" <% if(familiaAntes.equals("LBBAIC")){ %>
					selected="selected" <%}%>>LB,BA e IC</OPTION>
				<OPTION value="LBBATV" <% if(familiaAntes.equals("LBBATV")){ %>
					selected="selected" <%}%>>LB,BA y TV</OPTION>
				<OPTION value="LBBATVIC" <% if(familiaAntes.equals("LBBATVIC")){ %>
					selected="selected" <%}%>>LB,BA,TV e IC</OPTION>
			</SELECT></TD>
			<TD valign="middle" align="left" width="73"></TD>
			<TD valign="middle" width="151">Municipio</TD>
			<TD valign="middle" width="164"><SELECT name="municipio"
				onchange="cambioMunicipio();">
				<OPTION value="00000">Seleccione Municipio</OPTION>
				<%
				String valorMun="";
				if(request.getParameter("municipio")!=null)
					valorMun=request.getParameter("municipio");
				for(Iterator iterator=listaMunicipio.iterator();iterator.hasNext();)
				{
					MunicipioDTO municipioDTO=(MunicipioDTO)iterator.next();
					%>
				<OPTION
					<%=UtilesBandeja.selected(municipioDTO.getCod_mun(), valorMun)%>
					value="<%=municipioDTO.getCod_mun()%>"><%=municipioDTO.getNombre_municipio()%></OPTION>
				<%
				}
				%>
			</SELECT></TD>
			<TD valign="middle" align="left" width="73"></TD>
			<TD valign="middle">Estado</TD>
			<TD valign="middle" width="172"><SELECT name="otrostipos">
				<OPTION value="">Todas</OPTION>
				<%Iterator e = otrostipos.keySet().iterator();
				while (e.hasNext()) {
					String nombre = (String) e.next();
					String valorTipo = request.getParameter("otrostipos");%>
				<OPTION
					<%=UtilesBandeja.selected(otrostipos.get(nombre).toString(), valorTipo)%>
					value="<%=otrostipos.get(nombre).toString()%>"><%=nombre%></OPTION>

				<%}%>
			</SELECT></TD>

		</TR>

		<TR class="elementos-filtro" valign="top">
			<TD valign="middle">Rango Fecha</TD>
			<TD width="119"><INPUT type="text" name="fechaIni" size="10"
				readonly="readonly" value="<%=fechaIni%>"><IMG
				src="/bandejaweb/img/calendario.gif" border="0"
				onclick="javascript:NewCal('fechaIni','ddMMyyyy',false,24);"
				hspace="0" vspace="0" align="middle"><INPUT type="text"
				name="fechaFin" size="10" readonly="readonly" value="<%=fechaFin%>"><IMG
				src="/bandejaweb/img/calendario.gif" border="0"
				onclick="javascript:NewCal('fechaFin','ddMMyyyy',false,24);"
				hspace="0" vspace="0" align="middle"></TD>
			<TD nowrap="nowrap" width="73"></TD>
			<TD width="151" valign="top">Localidad</TD>
			<TD valign="top" width="164"><SELECT name="localidad"
				onchange="cambioLocalidad();">
				<OPTION value="00000000">Seleccione Localidad</OPTION>
				<%
			String valorLoc="";
			if(request.getParameter("localidad")!=null)
				valorLoc=request.getParameter("localidad");
			for(Iterator iterator=listLocalidad.iterator();iterator.hasNext();)
			{
				LocalidadDTO localidadDTO=(LocalidadDTO)iterator.next();
				%>
				<OPTION
					<%=UtilesBandeja.selected(localidadDTO.getCodLoc(), valorLoc)%>
					value="<%=localidadDTO.getCodLoc()%>"><%=localidadDTO.getNombreLocalidad()%></OPTION>
				<%
			}
			%>
			</SELECT></TD>
			<TD class="elementos-filtro" width="54" valign="middle"></TD>
			<TD nowrap="nowrap" width="73"></TD>
			<TD class="elementos-filtro" valign="middle" width="172"></TD>
		</TR>
	</TBODY>
</TABLE>
<!-- SEPARADOR FILTRO/LISTADO -->

<script>

function reenvioMasivo()
{
	if(!confirm("Esta accion realizará el reenvío Masivo de Mensajes . ¿Desea Continuar?"))
	{
		document.forma.action="verBandejaEsperaSigres.acc";
	 	document.forma.target = "";
	 	return;
	}
	blockButtonF();
	document.forma.action="<%=ApplicationConfig.getUrlBase(ApplicationConfig.APP_VPISTBBA) %>/RealizaReenvioMasivo?userAccion=<%=usuario.getUsername()%>&iduserAccion=<%=usuario.getId() %>";
	document.forma.target="";
	document.forma.submit();
	
	document.forma.action="verBandejaEsperaSigres.acc";
	document.forma.target = "";
	
	 	
}

</script>

<div style="font-size: 2px">&nbsp;</div>

<table class="tabla-borde-delgado" width="100%">
	<tr>
		<td class="elementos-filtro" align="left">
			<input type="button"
			onclick="reenvioMasivo();"
			name="btnMasivo" value="Realizar Reenvio Masivo" id="btnMasivo"
			class="botones-chicos"></td>
		<td class="elementos-filtro" align="right"><jsp:include
			page="/jsp/paginador.jsp" flush="true" /></td>
	</tr>
</table>

<!-- BLOQUE LISTADO PETICIONES -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="titulo-tabla">Listado de Peticiones</td>
	</tr>
</table>

<table id="table" class="listado" width="100%">
	<tr class="encabezados-tabla">
		<td><input type="checkbox" id="checkAll" name="checkAll"value="checkAll" onClick="ChequearTodos(this.checked)"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(1)" style="cursor: hand" alt="Orden Ascendentemente">NºAtis<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(2)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(3)" style="cursor: hand" alt="Orden Ascendentemente">NºAtiempo<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(4)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(5)" style="cursor: hand" alt="Orden Ascendentemente">Departamento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(6)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(7)" style="cursor: hand" alt="Orden Ascendentemente">Localidad<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(8)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(9)" style="cursor: hand" alt="Orden Ascendentemente">Actividad<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(10)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<td nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(11)" style="cursor: hand" alt="Orden Ascendentemente">Segmento<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(12)" style="cursor: hand" alt="Orden Descendentemente"></td>
		<TD nowrap="nowrap"><img src="/bandejaweb/img/i_p_sort_desc.gif" onclick="javascript:ordenar(13)" style="cursor: hand" alt="Orden Ascendentemente">Fecha Inicio<img src="/bandejaweb/img/i_p_sort_asc.gif" onclick="javascript:ordenar(14)" style="cursor: hand" alt="Orden Descendentemente"></TD>
		
	</tr>
			
<%
	
	if(listaBackOffice!=null)
	{
		int par=0;	
		for(Iterator iterator=listaBackOffice.iterator();iterator.hasNext();par++)
		{
			PeticionDTO peticionDTO=(PeticionDTO)iterator.next();
			%>
			<script language="JavaScript">
				 checkpeticiones[<%=par%>]=<%=peticionDTO.getBiNroPeticion()%>;
			</script>
			
			<input type="hidden" name="par<%=peticionDTO.getBiNroPeticion()%>"
				value="<%=peticionDTO.getBiNroPeticion()%>">
			<input type="hidden" name="petiAtis_<%=peticionDTO.getBiId()%>"
				value="<%=peticionDTO.getCod_pet_cd()%>">		
			<input type="hidden" name="peti<%=par%>"
				value="<%=peticionDTO.getBiNroPeticion()%>">
			
			<TR class="fila-detalle-<%=par%2 == 0 ? "par" : "impar"%>">
			<td nowrap="nowrap"><input type="checkbox" id="checkbox<%=par%>"
			name="checkbox<%=peticionDTO.getBiNroPeticion()%>"
			value="<%=peticionDTO.getBiNroPeticion()%>"></td>
			
			<td nowrap="nowrap"><%=peticionDTO.getCod_pet_cd()%></td>
			<td nowrap="nowrap">
				<A href="/vpistbbaweb/BuscadorPeticionesSigresCo.acc?folio=<%=peticionDTO.getBiNroPeticion()%>">
				<%=peticionDTO.getBiNroPeticion()%>
				</A>
			</td>
			<td nowrap="nowrap"><%=peticionDTO.getDepartamento()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getLocalidad()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getActividadDescripcion()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getSegmentoDescripcion()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getFechaInicioS()%></td>
		</TR>
			<%
			
		}
	}
	%>
	
</table>

<SCRIPT>

function blockButton(){
    var boton = document.getElementById('btnMasivo');
    
    boton.value = '     Cargando...      ';
    boton.disabled="disabled";
	setTimeout ('activeButton()', <%if (parameDto.getValorParametro() == null) {%> 60000 <% }else%><%=new Integer(parameDto.getValorParametro())%>);
	
}
function activeButton(){
    var boton = document.getElementById('btnMasivo');
    
    boton.value = 'Realizar Reenvio Masivo';
   	boton.disabled=false;

}

function blockButtonF(){
    var boton = document.getElementById('btnMasivo');
    var botonF = document.getElementById('botonFiltro');
    
    boton.value = '     Cargando...      ';
   // botonF.value = 'Cargando...';
    boton.disabled="disabled";
    botonF.disabled="disabled";
	setTimeout ('activeButtonF()', <%if (parameDto.getValorParametro() == null) { %> 60000 <% }else%><%=new Integer(parameDto.getValorParametro())%>);
	
}
function activeButtonF(){
    var boton = document.getElementById('btnMasivo');
    var botonF = document.getElementById('botonFiltro');
    
    boton.value = 'Realizar Reenvio Masivo';
    //botonF.value = ' Filtrar ';
   	boton.disabled=false;
   	botonF.disabled=false;

}


</SCRIPT>

</form>


