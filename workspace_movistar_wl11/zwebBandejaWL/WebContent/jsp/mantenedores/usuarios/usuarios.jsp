
<%@ page import="com.telefonica_chile.bandeja.mantenedores.usuarios.TablaUsuario" %>
<%@ page import="com.telefonica_chile.bandeja.dto.UsuarioDTO" %>
<%@ page import="com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios.MantenedorUsuariosAcc" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="usuarios" class="com.telefonica_chile.bandeja.mantenedores.usuarios.TablaUsuario" scope="request" />

<script language="JavaScript">
<!--
function buscar(forma) {
	resetPagina();
	forma.submit();
}
function limpiarFiltros(forma) {
	resetPagina();
	forma.FILTRO_ID.value = '';
	forma.FILTRO_LOGIN.value = '';
	forma.submit();
}
function eliminarUsuario(forma, id) {
	alert('No implementado aun');
	return false;
}
function nuevoUsuario(forma) {
	document.location.href='mantenedorUsuarios.acc?accion=<%= MantenedorUsuariosAcc.ACCION_FORMULARIO_NUEVO_USUARIO %>';
}
function editarUsuario(id) {
	document.location.href='mantenedorUsuarios.acc?accion=<%= MantenedorUsuariosAcc.ACCION_FORMULARIO_EDITAR_USUARIO %>&idUsuario=' + id;
}
// -->
</script>
<form name="forma" action="mantenedorUsuarios.acc" method="post">
<input type="hidden" name="accion" value="<%= MantenedorUsuariosAcc.ACCION_LISTAR %>">
<table class="tabla-borde-delgado" width="780">
	<tr><td class="titulo-tabla">Mantenedor de Usuarios</td></tr>
	<tr>
	  	<td class="elementos-filtro">
		</td>
	</tr>
<%
	String id = request.getParameter("FILTRO_ID");
	if (id == null) id = "";
	String login = request.getParameter("FILTRO_LOGIN");
	if (login == null) login = "";
%>
	<tr>
	  	<td class="elementos-filtro">
<!-- Filtro ---->
			<table width="100%">
				<tr> 
					<td class="elementos-filtro" align="right">Usuario</td>
					<td class="elementos-filtro">
					<input class="botones-chicos" type=text NAME="FILTRO_ID"
						maxlength="4" size="4" value="<%= id %>"></td>

					<td class="elementos-filtro" align="right">Nombre</td>
					<td class="elementos-filtro">
					<input class="botones-chicos" type=text name="FILTRO_LOGIN"
						maxlength=30 size=30 value="<%= login %>"></td>
					<TD valign="bottom" rowspan="4">
						<INPUT class="botones-chicos" type="button" value="Buscar" onClick='buscar(this.form)'>
						<INPUT class="botones-chicos" type="button" value="Limpiar" onclick="limpiarFiltros(this.form)">
					</TD>
					<td class="elementos-filtro" align="right">
						<jsp:include page="/jsp/paginador.jsp" flush="true"/>
						<!-- FIN PAGINADOR -->
					</td>
				</tr>
			</table>
<!-- fin del filtro -->    		
		</td>
	</tr>
	<tr>
		<td class="elementos-filtro" align="left">
			<jsp:include page="/jsp/mensajes.jsp" flush="true" />
		</td>
	</tr>
	<tr>
    <td class="elementos-filtro" align="center">
		<table class="tabla-borde-delgado" width="100%">
			<tr class="encabezados-tabla">
				<td colspan="2">
					<input class=botones-chicos onClick="nuevoUsuario(this.form)" type=button
							value="Crear Nuevo Registro">
				</td>
				<td title="Id Usuario">Id Usuario</td>
				<td title="Nombre Usuario">Nombre Usuario</td>
				<td title="Nombre Completo">Nombre Completo</td>
				<td title="Rut Usuario">Rut Usuario</td>
				<td title="Email">Email</td>
				<td title="Habilitado">Habilitado</td>
			</tr>
		<%
			int n = 0;
			for (Iterator it = usuarios.iterator(); it.hasNext(); ) {
				UsuarioDTO u = (UsuarioDTO) it.next();
				n++;
				String clase = n % 2 == 0 ? "fila-detalle-par" : "fila-detalle-impar";
		%>
			<tr class="<%= clase %>">
				<td>
					<a class="link-simple-oscuro" href="#"
							onClick="return eliminarUsuario(<%= u.getId() %>)">Eliminar</a>
				</td>
				<td>
					<a class="link-simple-oscuro"
							href="javascript:editarUsuario(<%= u.getId() %>)">Editar</a>
				</td>
				<td><%= u.getId() %></td>
				<td><%= u.getUsername() %></td>
				<td><%= u.getNombre() %></td>
				<td><%= u.getRut() %></td>
				<td><%= u.getEmail() %></td>
				<td><%= u.getHabilitado() %></td>
			</tr>
		<%
			}
			if (usuarios.size() == 0) {
		%>
			<tr class="fila-detalle-impar"><td colspan="8">No se encontraron datos</td></tr>
		<%
			}
		%>
		</table>
		</td>
	</tr>
</table>
</form>
</BODY>
</HTML>

		
