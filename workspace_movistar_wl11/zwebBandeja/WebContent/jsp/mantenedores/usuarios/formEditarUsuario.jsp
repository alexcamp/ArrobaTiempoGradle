<%@ page import="com.telefonica_chile.bandeja.mantenedores.usuarios.*" %>
<%@ page import="com.telefonica_chile.bandeja.dto.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios.MantenedorUsuariosAcc" %>

<jsp:useBean id="u" class="com.telefonica_chile.bandeja.dto.UsuarioDTO" scope="request" />
<jsp:useBean id="rolesNoAsociados" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="perfilesNoAsociados" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="camposNoAsociados" class="java.util.ArrayList" scope="request" />

<script language="javascript" src="/bandejaweb/js/jslib.js"></script>
<script language="javascript" src="/bandejaweb/js/mantenedores.js"></script>
<script language="javascript" src="/bandejaweb/js/mantenedor_usuarios.js"></script>
<table width="100%" class="elementos-filtro">
<form name="forma" action="mantenedorUsuarios.acc" method="post" >
<input type="hidden" name="accion" value="<%= MantenedorUsuariosAcc.ACCION_ACTUALIZAR %>">
	<tr>
		<td colspan=2  class=titulo-tabla>
			<table border=0 width=100%>
				<tr> 
					<td class=titulo-tabla>Mantenedor de Usuarios - Edici&oacute;n</td>
					<td class=titulo-tabla align='right' valign='middle' >
						<a href="mantenedorUsuarios.acc">
							<img src="/bandejaweb/img/home_blanco_17x17_alt.gif" border="0" vspace="0" hspace="0"
								width="17" height="17" alt="Ir al Listado"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr class="elementos-filtro">
		<td colspan="2">
			<jsp:include page="/jsp/mensajes.jsp" flush="true" />
		</td>
	</tr>
	<tr class="elementos-filtro"> 
		<td class=elementos-filtro colspan=2> 
			<table border="0" width='100%'>
				<tbody> 
					<tr> 
						<td class="elementos-filtro" >Id</td>
						<td class=elementos-filtro >
							:&nbsp;
							<input class='botones-chicos' type='text' name='idUsuario' value='<%= u.getId() %>' readonly>
						</td>
						<td class=elementos-filtro >Usuario</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=30 size=32 name='loginUsuario'
										value="<%= u.getUsername() %>" readonly>
						</td>
						<td class=elementos-filtro >Nombre</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=120 size=32 name='nombreCompleto'
										value="<%= u.getNombre() %>">
						</td>
					</tr>
					<tr> 
						<td class=elementos-filtro >Rut</td>
<%
	String[] rut = u.getPartesRut();
%>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=10 size=11 name='rutUsuario'
										value="<%= rut != null ? rut[0] : "" %>"> 
								- <input class='botones-chicos' maxlength=1 size=1 name='dgvUsuario'
										value="<%= rut != null ? rut[1] : "" %>">
						</td>
						<td class=elementos-filtro >Email</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=60 size=32 name='emailUsuario'
										value="<%= u.getEmail() %>">
						</td>
						<td class=elementos-filtro >Habilitado</td>
						<td class=elementos-filtro >
							S&iacute;<input type="radio" name="habilitado" value="S"
								<%= "S".equals(u.getHabilitado()) ?  "checked" : "" %>>
							&nbsp;
							No<input type="radio" name="habilitado" value="N"
								<%=  "S".equals(u.getHabilitado()) ? "" : "checked" %>>
						</td>
					</tr>
					<tr> 
						<td class=elementos-filtro >Direcci&oacute;n</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=200 size=32 name='direccionUsuario'
										value="<%= u.getDireccion() %>"> 
						</td>
						<td class=elementos-filtro >Tel&eacute;fono</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=60 size=32 name='fonoUsuario'
										value="<%= u.getFono() %>">
						</td>
						<td class=elementos-filtro >Cargo</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=30 size=32 name='cargoUsuario'
										value="<%= u.getCargo() %>">
						</td>
					</tr>
					<tr>
						<td class=elementos-filtro colspan='5'>
							<input type='hidden' class='botones-chicos' name='usEliminado' value='N'>
						</td>
						<td class='elementos-filtro' align='right'>
							<input class='botones-chicos' type='button' value='Guardar'
								onclick="javascript:actualizarUsuario(this.form);">
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td width='50%' valign=top>
			<table class="tabla-borde-delgado" width='100%'>
				<tr>
					<td class='encabezado-subtabla' align='center' width='100%' colspan=4>Roles</td>
				</tr>
				<tr>
					<td class="elementos-filtro" width='20%'>&nbsp;</td>				
					<td class="elementos-filtro" align='center' width='74%' colspan="2">Rol</td>
					<td class="elementos-filtro" width='6%'>&nbsp;</td>
				</tr>
				<tr>
					<td class='elementos-filtro' align='center'>
						<input class='botones-chicos' type='button' value='Agregar'
							onclick="javascript:agregarRol(this.form.idRol);">
					</td>
					<td class="elementos-filtro" align='center' colspan="2">
						<SELECT name='idRol'>
							<option value=''>-Seleccione-
<%
	for (Iterator it = rolesNoAsociados.iterator(); it.hasNext(); ) {
		RolDTO rol = (RolDTO) it.next();
%>
							<option value="<%= rol.getId() %>"><%= rol.getCodigo() %>
<%
	}
%>
						</SELECT>
					</td>
					<td class="elementos-filtro" align='center'>
						<a href='#'
							onclick='return muestraOcultaTabla("ocultaRol","minMaxRol", document.forma.rolvisible);'>
							<img id='minMaxRol' name='minMaxRol' alt='Ocultar' border='0' src='/bandejaweb/img/restaurar.gif'>
						</a>
						<input type="hidden" name="rolvisible" value="<%= request.getParameter("rolvisible") %>">
					</td>
				</tr>
				<tr>
					<td class='elementos-filtro' colspan='4'>
						<table width='100%' id='ocultaRol' style='display:none'>
<%
	boolean par = false;
	for (Iterator it = u.getRoles().iterator(); it.hasNext(); ) {
		RolDTO rol = (RolDTO) it.next();
		String clase = par ? "fila-detalle-par" : "fila-detalle-impar";
		par = !par;
%>
							<tr>
								<td class="<%= clase %>" width='20%'>
								<a class='link-simple-oscuro' href="#" onClick="eliminarRol(<%= rol.getId() %>)">Eliminar</a>
								</td>
								<td class="<%= clase %>" width='80%' colspan="2"><%= rol.getCodigo() %></td>
								<td width='6%'>

								</td>
							</tr>
<%
	}
%>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td width='50%' valign=top>
			<table class="tabla-borde-delgado" width='100%'>
				<tr>
					<td class='encabezado-subtabla' align='center' width='100%' colspan=4>Perfiles</td>
				</tr>
				<tr>
					<td class="elementos-filtro" width='20%'>&nbsp;</td>				
					<td class="elementos-filtro" align='center' width='74%' colspan="2">Perfil</td>
					<td class="elementos-filtro" width='6%'>&nbsp;</td>
				</tr>
				<tr>
					<td class='elementos-filtro' align='center'>
						<input class='botones-chicos' type='button' value='Agregar'
							onclick="javascript:agregarPerfil(this.form.idPerfil);">
					</td>
					<td class="elementos-filtro" align='center' colspan="2">
						<SELECT name='idPerfil'>
							<option value=''>-Seleccione-
<%
	for (Iterator it = perfilesNoAsociados.iterator(); it.hasNext(); ) {
		PerfilDTO perfil = (PerfilDTO) it.next();
%>
							<option value='<%= perfil.getId() %>'><%= perfil.getDescripcion() %>
<%
	}
%>
						</SELECT>
					</td>
					<td class="elementos-filtro" align='center'>
						<a href='#'
							onclick='return muestraOcultaTabla("ocultaPerfil","minMaxPerfil", document.forma.perfilvisible);'>
							<img id='minMaxPerfil' name='minMaxPefil' alt='Ocultar' border='0' src='/bandejaweb/img/restaurar.gif'>
						</a>
						<input type="hidden" name="perfilvisible" value="<%= request.getParameter("perfilvisible") %>">
					</td>
				</tr>
				<tr>
					<td class='elementos-filtro' colspan='4'>
						<table width='100%' id='ocultaPerfil' style='display:none'>
<%
	par = false;
	for (Iterator it = u.getPerfiles().iterator(); it.hasNext(); ) {
		PerfilDTO perfil = (PerfilDTO) it.next();
		String clase = par ? "fila-detalle-par" : "fila-detalle-impar";
		par = !par;
%>
							<tr>
								<td class="<%= clase %>" width='20%'>
								<a class='link-simple-oscuro' href="#" onClick="eliminarPerfil(<%= perfil.getId() %>)">Eliminar</a>
								</td>
								<td class="<%= clase %>" width='80%'><%= perfil.getDescripcion() %></td>
							</tr>
<%
	}
%>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width='100%' valign="top" colspan="2">
			<table class="tabla-borde-delgado" width='100%'>
				<tr>
					<td class='encabezado-subtabla' align='center' width='100%' colspan=4>Supervisores</td>
				</tr>
				<tr>
					<td class="elementos-filtro" width='10%'>&nbsp;</td>
					<td class="elementos-filtro" align='center' width='40%'>Rol Supervisi&oacute;n</td>
					<td class="elementos-filtro" align='center' width='40%'>Usuario Supervisor</td>
					<td class="elementos-filtro" width='10%'>&nbsp;</td>
				</tr>
				<tr>
					<td class='elementos-filtro' align='center'>
						<input class='botones-chicos' type='button' value='Agregar'
							onclick="javascript:agregarSup(this.form);">
					</td>
					<td class="elementos-filtro" align='center'>
						<SELECT name='idRolSup' onChange="limpiaUsuario(this.form)">
							<option value=''>-Seleccione-
<%
	for (Iterator it = u.getRoles().iterator(); it.hasNext(); ) {
		RolDTO rol = (RolDTO) it.next();
%>
							<option value="<%= rol.getId() %>"><%= rol.getCodigo() %>
<%
	}
%>
						</SELECT>
					</td>
					<td class="elementos-filtro" align='center'>
						<input class="botones-chicos" type="hidden" name="idUsuSup">
						<input class="botones-chicos" type="text" name="usernameBusqueda">
						<a href="#" 
						onClick="abreBusquedaUsuarios(<%= MantenedorUsuariosAcc.ACCION_BUSCAR_USUARIO_SUPERVISOR %>, <%= u.getId() %>)">
							<img src="/bandejaweb/img/boton_up.gif" alt="Buscar Usuario" align="absmiddle" border="0">
						</a>
					</td>
					<td class="elementos-filtro" align="right">
						<a href='#'
							onclick='return muestraOcultaTabla("ocultaSup","minMaxSup", document.forma.supvisible);'>
							<img id='minMaxSup' name='minMaxSup' alt='Ocultar' border='0' src='/bandejaweb/img/restaurar.gif'>
						</a>
						<input type="hidden" name="supvisible" value="<%= request.getParameter("supvisible") %>">
					</td>
				</tr>
				<tr>
					<td class='elementos-filtro' colspan='4'>
						<table width='100%' id='ocultaSup' style='display:none'>
<%
	par = false;
	for (Iterator it = u.getParesSupervisor().iterator(); it.hasNext(); ) {
		ParUsuarioRol ur = (ParUsuarioRol) it.next();
		String estilo = par ? "fila-detalle-par" : "fila-detalle-impar";
		par = !par;
%>
							<tr class="<%= estilo %>">
								<td width='10%'>
								<a class='link-simple-oscuro' href="#"
								 onClick="eliminarSup(<%= ur.getUsuario().getId() %>, <%= ur.getRol().getId() %>)">
								 Eliminar</a>
								</td>
								<td align='center' width='40%'><%= ur.getUsuario().getUsername() %></td>
								<td align='center' width='50%' colspan="2"><%= ur.getRol().getCodigo() %></td>
							</tr>
<%
	}
%>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width='100%' valign=top colspan="2">
			<table class="tabla-borde-delgado" width='100%'>
				<tr>
					<td class='encabezado-subtabla' align='center' width='100%' colspan=3>Campos por Usuario</td>
				</tr>
				<tr>
					<td class="elementos-filtro" width='10%'>&nbsp;</td>
					<td class="elementos-filtro" align='center' width='80%'>Campo variable</td>
					<td class="elementos-filtro" width='10%'>&nbsp;</td>
				</tr>
				<tr>
					<td class='elementos-filtro' align='center'>
						<input class='botones-chicos' type='button' value='Agregar'
							onclick="javascript:agregarCampo(this.form.idCampo);">
					</td>
					<td class="elementos-filtro" align='center'>
						<SELECT name='idCampo'>
							<option value=''>-Seleccione-
<%
	for (Iterator it = camposNoAsociados.iterator(); it.hasNext(); ) {
		CampoDTO campo = (CampoDTO) it.next();
%>
							<option value="<%= campo.getId() %>"><%= campo.getNombreInterno() + " - " + campo.getNombreExterno() %>
<%
	}
%>
						</SELECT>
					</td>
					<td class="elementos-filtro" align="right">
						<a href='#'
							onclick='return muestraOcultaTabla("ocultaCampos","minMaxCampos", document.forma.camposvisible);'>
							<img id='minMaxCampos' name='minMaxCampos' alt='Ocultar' border='0' src='/bandejaweb/img/restaurar.gif'>
						</a>
						<input type="hidden" name="camposvisible" value="<%= request.getParameter("camposvisible") %>">
					</td>
				</tr>
				<tr>
					<td class='elementos-filtro' colspan='4'>
						<table width='100%' id='ocultaCampos' style='display:none'>
<%
	par = false;
	for (Iterator it = u.getCamposVariables().iterator(); it.hasNext(); ) {
		CampoDTO campo = (CampoDTO) it.next();
		String estilo = par ? "fila-detalle-par" : "fila-detalle-impar";
		par = !par;
%>
							<tr class="<%= estilo %>">
								<td width='10%'>
								<a class='link-simple-oscuro' href="#"
								 onClick="eliminarCampo(<%= campo.getId() %>)">
								 Eliminar</a>
								</td>
								<td align='center' width='90%' colspan="2">
									<%= campo.getNombreInterno() + "-" + campo.getNombreExterno() %>
								</td>
							</tr>
<%
	}
%>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>

<script language="JavaScript">
<!--
iniciaMuestraOcultaTabla("ocultaRol","minMaxRol", document.forma.rolvisible);
iniciaMuestraOcultaTabla("ocultaPerfil","minMaxPerfil", document.forma.perfilvisible);
iniciaMuestraOcultaTabla("ocultaSup","minMaxSup", document.forma.supvisible);
iniciaMuestraOcultaTabla("ocultaCampos","minMaxCampos", document.forma.camposvisible);
// -->
</script>
<input type="hidden" name="idRolEliminar">
<input type="hidden" name="idPerfilEliminar">
<input type="hidden" name="idUsuarioJerEliminar">
<input type="hidden" name="idRolJerEliminar">
<input type="hidden" name="idCampoEliminar">
	</form>

</table>
</BODY>
</HTML>


