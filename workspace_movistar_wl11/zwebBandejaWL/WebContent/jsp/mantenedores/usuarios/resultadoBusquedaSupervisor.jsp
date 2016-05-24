<%@ page import="com.telefonica_chile.bandeja.mantenedores.usuarios.TablaBusquedaSupervisores" %>
<%@ page import="com.telefonica_chile.bandeja.dto.UsuarioDTO" %>
<%@ page import="com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios.MantenedorUsuariosAcc" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="usuarios" class="com.telefonica_chile.bandeja.mantenedores.usuarios.TablaBusquedaSupervisores" scope="request" />
<link rel="stylesheet" href="/bandejaweb/estilo.css" />
<form name="forma" action="mantenedorUsuarios.acc" method="post">
<input type="hidden" name="accion" value="<%= MantenedorUsuariosAcc.ACCION_BUSCAR_USUARIO_SUPERVISOR %>">
<%
	String idRol = request.getParameter("idRol");
	if (idRol == null) idRol = "";

	String idUsuSub = request.getParameter("idUsuSub");
	if (idUsuSub == null) idUsuSub = "";

	String usernameBusqueda = request.getParameter("usernameBusqueda");
	if (usernameBusqueda == null) usernameBusqueda = "";
%>
<input type="hidden" name="idRol" value="<%= idRol %>">
<input type="hidden" name="idUsuSub" value="<%= idUsuSub %>">
<input type="hidden" name="usernameBusqueda" value="<%= usernameBusqueda %>">

<table class="tabla-borde-delgado" width="680">
	<tr><td class="titulo-tabla">Resultado de B&uacute;squeda</td></tr>
	<tr>
	  	<td class="elementos-filtro">
		</td>
	</tr>
	<tr>
		<td class="elementos-filtro" align="left">
			<jsp:include page="/jsp/mensajes.jsp" flush="true" />
		</td>
	</tr>
	<tr>
		<td class="elementos-filtro" align="right">
			<!-- INICIO PAGINADOR -->
			<jsp:include page="/jsp/paginador.jsp" flush="true" />
			<!-- FIN PAGINADOR -->
		</td>
	</tr>
	<tr>
    <td class="elementos-filtro" align="center">
		<!-- tabla-->
		<table class="tabla-borde-delgado" width="680">
			<tr class="encabezados-tabla">
				<td title="Id Usuario" width="15%">Id Usuario</td>
				<td title="Nombre Usuario" width="30%">Nombre Usuario</td>
				<td title="Nombre Completo" width="55%">Nombre Completo</td>
			</tr>
		<%
			int n = 0;
			for (Iterator it = usuarios.iterator(); it.hasNext(); ) {
				UsuarioDTO u = (UsuarioDTO) it.next();
				n++;
				String clase = n % 2 == 0 ? "fila-detalle-par" : "fila-detalle-impar";
		%>
			<tr class="<%= clase %>">
				<td><a href="#" onClick="javascript:escogeUsuario(<%= u.getId() %>, '<%= u.getUsername() %>')"><%= u.getId() %></a></td>
				<td><a href="#" onClick="javascript:escogeUsuario(<%= u.getId() %>, '<%= u.getUsername() %>')"><%= u.getUsername() %></a></td>
				<td><a href="#" onClick="javascript:escogeUsuario(<%= u.getId() %>, '<%= u.getUsername() %>')"><%= u.getNombre() %></a></td>
			</tr>
		<%
			}
			if (usuarios.size() == 0) {
		%>
			<tr><td class="fila-detalle-impar" colspan="3">No se encontraron datos. <a href="#" onClick="window.close()">Cerrar</a></td></tr>
		<%
			}
		%>
		</table>
		<!-- Fin tabla -->
	</td>
</tr>
</table>
</form>
<script language="JavaScript">
<!--
function escogeUsuario(id, username) {
	window.opener.document.forma.idUsuSup.value = id;
	window.opener.document.forma.usernameBusqueda.value = username;
	window.close();
}
// -->
</script>

		
