<%@ page import="com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios.MantenedorUsuariosAcc" %>
<script language="javascript" src="/bandejaweb/js/jslib.js"></script>
<script language="javascript" src="/bandejaweb/js/mantenedores.js"></script>
<script language="javascript" src="/bandejaweb/js/mantenedor_usuarios.js"></script>
<table width=780 class="elementos-filtro">
<form name="forma" action="mantenedorUsuarios.acc" method="post" >
<input type="hidden" name="accion" value="<%= MantenedorUsuariosAcc.ACCION_INSERTAR %>">
	<tr> 
		<td colspan=2  class=titulo-tabla>
			<table border=0 width=100%>
				<tr> 
					<td class=titulo-tabla>Mantenedor de Usuarios - Ingreso</td>
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
			<table border=0 width='100%'>
				<tbody> 
					<tr> 
						<td class=elementos-filtro >Id</td>
						<td class=elementos-filtro >
							:&nbsp;
							<input class='botones-chicos' type='hidden' name='idUsuario'>
						</td>
						<td class=elementos-filtro >Usuario</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=30 size=32 name='loginUsuario' onChange='muestraMayusculas(this)'>
						</td>
						<td class=elementos-filtro >Nombre</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=120 size=32 name='nombreCompleto'>
						</td>
					</tr>
					<tr> 
						<td class=elementos-filtro >Rut</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=10 size=11 name='rutUsuario'> 
								- <input class='botones-chicos' maxlength=1 size=1 name='dgvUsuario'>
						</td>
						<td class=elementos-filtro >Email</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=60 size=32 name='emailUsuario'>
						</td>
						<td class=elementos-filtro >Password</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=30 size=32 name='pswUsuario' onChange='muestraMayusculas(this)'>
						</td>
					</tr>
					<tr> 
						<td class=elementos-filtro >Direcci&oacute;n</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=200 size=32 name='direccionUsuario'> 
						</td>
						<td class=elementos-filtro >Tel&eacute;fono</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=60 size=32 name='fonoUsuario'>
						</td>
						<td class=elementos-filtro >Cargo</td>
						<td class=elementos-filtro >
							:&nbsp;<input class='botones-chicos' maxlength=30 size=32 name='cargoUsuario'>
						</td>
					</tr>
					<tr>
						<td class=elementos-filtro colspan='5'>
							<input type='hidden' class='botones-chicos' name='usEliminado' value='N'>
						</td>
						<td class='elementos-filtro' align='center'>
							<input class='botones-chicos' type='button' value='Guardar'
								onclick="javascript:agregarUsuario(this.form);">
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	</form>	
</table>
</BODY>
</HTML>


