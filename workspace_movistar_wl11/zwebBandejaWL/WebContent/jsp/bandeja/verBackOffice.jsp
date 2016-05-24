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
<%@page import="co.com.atiempo.dto.EstadoPsPeticionDTO"%>
<%@page import="co.com.telefonica.atiempo.web.utiles.WebUtil" %>
<%@page import="java.text.*"%>
<jsp:useBean id="informacionPeticion" class="co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO" scope="request" />

<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap,java.util.Hashtable,java.util.Vector"%>

<%

UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
Tabla listaBackOffice = (Tabla) request.getAttribute("listaBackOffice");

//REQ 32378 - 2010/01/28 - FAVG - INICIO 
Hashtable novedades = (Hashtable) request.getAttribute("hashNovedades");
//REQ 32378 - 2010/01/28 - FAVG - FIN 

ArrayList listLocalidad=(ArrayList)request.getAttribute("listLocalidad");
ArrayList listaMunicipio=(ArrayList)request.getAttribute("listaMunicipio");
ArrayList listDepartamento=(ArrayList)request.getAttribute("listaDepartamento");
ArrayList listAplicaciones = (ArrayList) request.getAttribute("listAplicaciones");
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

<script type="text/javascript"> 
	
	<%
	//REQ 32378 - 2010/01/28 - FAVG - INICIO 	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	java.util.Enumeration novs = novedades.elements();
	while (novs.hasMoreElements()) 
	{		
		Vector his = (Vector)novs.nextElement();
		int x = 0;	
		EstadoPsPeticionDTO estadoPsPet = (EstadoPsPeticionDTO)his.firstElement();
	%>	
	function replace<%=estadoPsPet.getPetiNumero()%>() {
	if(document.forma.control.value != " "){
		document.getElementById('divNov').innerHTML = "";
	}
	document.getElementById('divNov').innerHTML = "<TABLE border=\"1\" class=\"contenido\"> "+		
			"<TR class=\"encabezados-tabla\" > "+
				"<TD colspan=8>Quiebres y Novedades</TD> "+
			"</TR> "+
			"<TR class=\"elementos-filtro\" > "+
				"<TD>No. Atiempo</TD> "+
				"<TD>Id PS</TD> "+
				"<TD>Nombre PS</TD> "+
				"<TD>Actividad</TD> "+
				"<TD>Causal/Novedad</TD> "+
				"<TD>Fecha Termino</TD> "+
				"<TD>Usuario</TD> "+
				"<TD>Observaciones</TD> "+
			"</TR> "+
<%			
		for(Iterator iteratorHis = his.iterator(); iteratorHis.hasNext();)		
		{
			estadoPsPet = (EstadoPsPeticionDTO)iteratorHis.next();			
			x++;	
			if(x%2==0){	
%>
		"<TR class=\"fila-detalle-par\"> "+
<% 
			}else{
%>
		"<TR class=\"fila-detalle-impar\"> "+
<% 			} 
%>
			"<TD><%=estadoPsPet.getPetiNumero()%></b></td> "+
			"<TD><%=WebUtil.getString(estadoPsPet.getCodPs())%></td> "+
			"<TD><%=WebUtil.getString(estadoPsPet.getDescPs())%></TD> "+
			"<TD><%=WebUtil.getString(estadoPsPet.getDescAct())%></TD> "+
			"<TD><%=WebUtil.getString(estadoPsPet.getDescCausa())%></TD> "+
			"<TD><%=WebUtil.getString(estadoPsPet.getFechaTermino())%></TD> "+
			"<TD><%=WebUtil.getString(estadoPsPet.getUsuaNombre())%></TD> "+			
			"<TD><%=WebUtil.getString(estadoPsPet.getNovedad())%></TD> "+
		"</TR> "+
<%
		}
%>
	"</TABLE> ";
	document.forma.control.value="<%=estadoPsPet.getPetiNumero()%>";
	}
<%
	}
	//REQ 32378 - 2010/01/28 - FAVG - FIN
%>
	
</script>

<script language="JavaScript">
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
	document.forma.otrostipos.value="";
	document.forma.estado.value="0";
	document.forma.familiaPetiAtis.value="00";
	document.forma.orden.value="1";
	document.forma.opcocat.value="0";
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

<form name="forma" action="verBandejaBackOC.acc" method="post">
<input type="hidden" id="control" name="control" value=" ">
<input type="hidden"  name="orden" value="1">
<input type="hidden"  name="realizarBusqueda" value="1">
<INPUT type="hidden"  name="fechaHoyInicial" value="<%=fecha.getFechaconFormato(8) %>">
	<table id="filtro-grande" width="100%">
	<tr class="titulo-tabla">
		<td colspan="10" class="titulo-tabla">Filtro de Peticiones</td>
		<td align="right" valign="bottom" width="132"><a
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
		<TD valign="middle" align="left" width="133"></TD>
		<TD valign="middle" align="left" width="27"></TD>
		<td valign="middle" width="78">Departamento</td>
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
		<TD valign="middle" width="54">Ambito</TD>
		<TD valign="middle" width="148">
			:<select name="aplicacion">
				<%for (Iterator it = listAplicaciones.iterator(); it.hasNext();)
				{
					AplicacionDTO aplicacion = (AplicacionDTO) it.next();
					if(aplicacion.getApId().intValue()==1 || aplicacion.getApId().intValue()==2)
						continue;
					String valorAplicacion = request.getParameter("aplicacion");%>
				<option <%=UtilesBandeja.selected(aplicacion.getApId().toString(), valorAplicacion)%>
					value="<%=aplicacion.getApId()%>"><%=aplicacion.getApNombre()%></option>
				<%}%>
			</select>
		</TD>
			
		<td class="elementos-filtro" rowspan="3" valign="middle" width="5">
		<div align="center"><img src="/bandejaweb/img/separador_vertical.gif"
			height="60" width="2" align="middle"></div>
		</td>

		<td rowspan="3" width="132">
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
				<OPTION value="BATV" <% if(familiaAntes.equals("BATV")){ %> selected="selected" <%}%>>BA y TV</OPTION>
				<OPTION value="BATVIC" <% if(familiaAntes.equals("BATVIC")){ %> selected="selected" <%}%>>BA,TV e IC</OPTION>
				<OPTION value="LBIC" <% if(familiaAntes.equals("LBIC")){ %> selected="selected" <%}%>>LB e IC</OPTION>
				<OPTION value="LBTV" <% if(familiaAntes.equals("LBTV")){ %> selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="LBBA" <% if(familiaAntes.equals("LBBA")){ %> selected="selected" <%}%>>LB y BA</OPTION>
				<OPTION value="LBTVIC" <% if(familiaAntes.equals("LBTVIC")){ %> selected="selected" <%}%>>LB,TV e IC</OPTION>
				<OPTION value="LBBAIC" <% if(familiaAntes.equals("LBBAIC")){ %> selected="selected" <%}%>>LB,BA e IC</OPTION>
				<OPTION value="LBBATV" <% if(familiaAntes.equals("LBBATV")){ %> selected="selected" <%}%>>LB,BA y TV</OPTION>
				<OPTION value="LBBATVIC" <% if(familiaAntes.equals("LBBATVIC")){ %> selected="selected" <%}%>>LB,BA,TV e IC</OPTION>
			</SELECT></td>
		<TD valign="middle" align="left" colspan="2">Op. Com:
			<%
			String opcocat="0";
			if(request.getParameter("opcocat")!=null)
				opcocat=request.getParameter("opcocat");
			%>
			<select name="opcocat" id="opcocat">
				<OPTION value="0" <% if(opcocat.equals("0")){ %> selected="selected" <%}%>>Seleccione Tipo Op</OPTION>
				<OPTION value="A" <% if(opcocat.equals("A")){ %> selected="selected" <%}%>>Alta</OPTION>
				<OPTION value="T" <% if(opcocat.equals("T")){ %> selected="selected" <%}%>>Transferencia</OPTION>
				<OPTION value="R" <% if(opcocat.equals("R")){ %> selected="selected" <%}%>>Reconexión</OPTION>
				<OPTION value="S" <% if(opcocat.equals("S")){ %> selected="selected" <%}%>>Suspensión</OPTION>
				<OPTION value="P" <% if(opcocat.equals("P")){ %> selected="selected" <%}%>>PostVenta</OPTION>
			</select></TD>
		<td valign="middle" width="78">Municipio</td>
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
		<td class="elementos-filtro" width="54" valign="middle">Estado</td>
		<td class="elementos-filtro" valign="middle" width="148">:<select name="otrostipos">
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
	</tr>

	<tr class="elementos-filtro">
		<td valign="middle">Rango Fecha</td>
		<td width="137">:&nbsp; <INPUT type="text" name="fechaIni" size="10" readonly="readonly" value='<%=fechaIni%>'><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaIni','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"> -</td>
		<TD nowrap="nowrap" width="133"><INPUT type="text" name="fechaFin" size="10" readonly="readonly" value='<%=fechaFin%>' ><img src="/bandejaweb/img/calendario.gif" border="0" onClick="javascript:NewCal('fechaFin','ddMMyyyy',false,24);" hspace="0" vspace="0" align="middle"></TD>
		<TD nowrap="nowrap" width="27"></TD>
		<td valign="middle" width="78">Localidad</td>
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
		<td class="elementos-filtro" width="54" valign="middle">Gestion</td>
		<td class="elementos-filtro" valign="middle" width="148">
		:<%
			String estadoAntes="";
			if(request.getParameter("estado")!=null)
				estadoAntes=request.getParameter("estado");
		%>
		<SELECT name="estado">
			<OPTION value="0" <% if(estadoAntes.equals("0")){ %> selected="selected" <%}%>>--Todos--</OPTION>
			<OPTION value="1" <% if(estadoAntes.equals("1")){ %> selected="selected" <%}%>>Con novedad</OPTION>
			<OPTION value="2" <% if(estadoAntes.equals("2")){ %> selected="selected" <%}%>>Con quiebre</OPTION>
			<OPTION value="3" <% if(estadoAntes.equals("3")){ %> selected="selected" <%}%>>OK</OPTION>
		</SELECT>
		</td>
	</tr>

</table>
<!-- SEPARADOR FILTRO/LISTADO -->
<div style="font-size: 2px">&nbsp;</div>

<!-- BLOQUE LISTADO PETICIONES -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="titulo-tabla">Listado de Peticiones</td>
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
		<TD nowrap="nowrap">Novedades</TD>
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
				<A href="/vpistbbaweb/BuscadorPeticionesCo.acc?folioAtis=<%=peticionDTO.getCod_pet_cd()%>">
				<%=peticionDTO.getCod_pet_cd()%>
				</A>
			</td>
			<td nowrap="nowrap">
				<A href="/vpistbbaweb/BuscadorPeticionesCo.acc?folio=<%=peticionDTO.getBiNroPeticion()%>">
				<%=peticionDTO.getBiNroPeticion()%>
				</A>
			</td>
			<td nowrap="nowrap"><%=peticionDTO.getDepartamento()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getLocalidad()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getSegmentoDescripcion()%></td>
			<td nowrap="nowrap"><%=peticionDTO.getSubSegmentoDescripcion()%></td>
		<TD nowrap="nowrap"><%=peticionDTO.getFechaInicioS()%></TD>
		<TD nowrap="nowrap"><%=peticionDTO.getFechaFinS()%></TD>
		<%
		if(novedades.containsKey(peticionDTO.getBiNroPeticion())){
			Vector his = (Vector)novedades.get(peticionDTO.getBiNroPeticion());
		%>
			
			<TD nowrap="nowrap"><a href="javascript:replace<%=peticionDTO.getBiNroPeticion()%>()">Ver</a></TD>
			
		<%		
		}else{
		%>		
			<TD nowrap="nowrap">--</TD>
		<%		
		}
		%>		
	</TR>
			<%
			k++;
		}
	}
	%>
</table>
<br>

	<div id="divNov">	
	</div>

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