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
<%@page import="com.telefonica_chile.bandeja.dto.UserCoDto"%>
<%@page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap,java.util.TreeMap"%>

<%

UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
Tabla listaBandejaSupCo = (Tabla) request.getAttribute("listaBandejaSupCo");

//ArrayList listAplicaciones = (ArrayList) request.getAttribute("listAplicaciones");

ArrayList listRolesUsuario = (ArrayList) request.getAttribute("listRolesUsuario");
TreeMap otrostipos = (TreeMap) request.getAttribute("otrostipos");
String usua_id=(String)request.getAttribute("usua_id");
%>
<!-- INICIO FILTRO -->
<script language="JavaScript">
<!--
function filtrarUsuSup()
{
	document.forma.submit();
}
function limpiarFiltro()
{
	document.forma.rol.value="";
	document.forma.submit();
}
// -->
</script>
<table id="filtro-chico" style="display: none" width="100%">
	<tr class="titulo-tabla">
		<td class="titulo-tabla">Filtro de Usuarios:</td>
		<td align="right" valign="bottom"><a
			href="javascript:muestra_filtro();" class="link-simple-filtro"
			title="Mostrar Filtro Completo"> <img
			src="/bandejaweb/img/restaurar.gif" height="14" border="0"> </a></td>
	</tr>
</table>

<form name="forma" action="verBandejaSupCo.acc" method="post">
<input type="hidden"  name="realizarBusqueda" value="1">
<input type="hidden"  name="usua_id" value="<%=usua_id%>">

	<table id="filtro-grande" width="100%">
	<tr class="titulo-tabla">
		<td colspan="5" class="titulo-tabla">Filtro de Usuarios:</td>
		<td align="right" valign="bottom" width="144"><a
			href="javascript:oculta_filtro();" class="link-simple-filtro"
			title="Ocultar Filtro"> <img src="/bandejaweb/img/minimizar.gif"
			height="14" border="0"> </a></td>
	</tr>
	<tr class="elementos-filtro">
		<td valign="middle" width="65">Rol:</td>
		<td valign="middle" width="176">
			:&nbsp;<SELECT name="rol">
			<OPTION value="">Todos los Roles</OPTION>
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
		</SELECT></td>
		<TD valign="middle" width="74"><!--Ambito--></TD>
		<TD valign="middle" width="460"><!--
			:<select name="aplicacion">-->
				<%--for (Iterator it = listAplicaciones.iterator(); it.hasNext();)
				{
					AplicacionDTO aplicacion = (AplicacionDTO) it.next();
					if(aplicacion.getApId().intValue()==1 || aplicacion.getApId().intValue()==2)
						continue;
					String valorAplicacion = request.getParameter("aplicacion");--%>
				<!--<option --><%--=UtilesBandeja.selected(aplicacion.getApId().toString(), valorAplicacion)--%>
				<!--	value="<%--=aplicacion.getApId()--%>"><%--=aplicacion.getApNombre()--%></option>-->
				<%--}--%>
			<!--</select>-->
		</TD>

		<td class="elementos-filtro" valign="middle" width="7">
		<div align="center"><img src="/bandejaweb/img/separador_vertical.gif"
			height="60" width="2" align="middle"></div>
		</td>

		<td width="144">
		<div align="center">Mostrar <select name="dpp">
			<%for (int i = 10; i <= 50; i += 10) {
%>
			<option value="<%=i%>"
				<%=String.valueOf(i).equals(request.getParameter("dpp")) ? "selected" : ""%>><%=i%></option>
			<%}
%>
		</select>&nbsp;d.&nbsp;p.&nbsp;pág.<br>
		<br>
		<input type="button" name="botonFiltro" onclick="filtrarUsuSup();"
			value=" Filtrar " class="botones-chicos"> &nbsp;&nbsp; <input
			type="button" name="botonFiltro" onclick="limpiarFiltro();"
			value=" Limpiar " class="botones-chicos"></div>
		</td>
	</tr>

</table>
<!-- SEPARADOR FILTRO/LISTADO -->
<div style="font-size: 2px">&nbsp;</div>

<!-- BLOQUE LISTADO PETICIONES -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="titulo-tabla">Listado de Usuarios:</td>
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
		<td nowrap="nowrap">Id</td>
		<TD nowrap="nowrap">Login</TD>
		<td nowrap="nowrap">Nombre</td>
		<TD nowrap="nowrap">NroPeticiones</TD>
	</tr>
	<%
	if(listaBandejaSupCo!=null)
	{
		int k=0;
		for(Iterator iterator=listaBandejaSupCo.iterator();iterator.hasNext();)
		{
			UserCoDto dto=(UserCoDto)iterator.next();
			%>
			<TR class="fila-detalle-<%=k%2==1 ? "par" : "impar"%>">
			<td nowrap="nowrap"><%=dto.getUsuaId()%></td>
			<TD nowrap="nowrap"><%=dto.getUsuaLogin()%></TD>
			<td nowrap="nowrap"><%if(dto.getUsuaNombre()!=null){%><%=dto.getUsuaNombre()%><%}%></td>
			<TD nowrap="nowrap"><a href="/bandejaweb/peticionesSupervisado.acc?usua_id=<%=usua_id%>&usua_supervisado=<%=dto.getUsuaId()%>&usuasupervisadologin=<%=dto.getUsuaLogin()%>"><%=dto.getNroPeticiones()%></a></TD>
			</TR>
			<%
			k++;
		}
	}
	%>
</table>

</form>