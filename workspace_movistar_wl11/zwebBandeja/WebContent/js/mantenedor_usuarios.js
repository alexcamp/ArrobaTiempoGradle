function agregarRol(selectRol) {
	if (selectRol.selectedIndex == 0) {
		alert('Seleccione rol para agregar');
		return false;
	}
	selectRol.form.submit();
}

function agregarPerfil(selectPerfil) {
	if (selectPerfil.selectedIndex == 0) {
		alert('Seleccione perfil para agregar');
		return false;
	}
	selectPerfil.form.submit();
}
function agregarCampo(selectCampo) {
	if (selectCampo.selectedIndex == 0) {
		alert('Seleccione campo para agregar');
		return false;
	}
	selectCampo.form.submit();
}

function eliminarRol(idRol) {
	var forma = document.forma;
	if (confirm('Esta seguro que desea sacar este rol del usuario?')) {
		forma.idRolEliminar.value = idRol;
		forma.submit();
	}
	return false;
}
function eliminarPerfil(idPerfil) {
	var forma = document.forma;
	if (confirm('Esta seguro que desea sacar este perfil del usuario?')) {
		forma.idPerfilEliminar.value = idPerfil;
		forma.submit();
	}
	return false;
}

function eliminarCampo(idCampo) {
	var forma = document.forma;
	if (confirm('Esta seguro que desea sacar este campo del usuario?')) {
		forma.idCampoEliminar.value = idCampo;
		forma.submit();
	}
	return false;
}

function agregarUsuario(forma) {
	if (checkField(forma.loginUsuario, isName, false, 'Corrija Usuario')
		&& checkField(forma.pswUsuario, isName, false, 'Corrija Password')
		&& checkField(forma.nombreCompleto, isName, false, 'Corrija Nombre')
		&& checkField(forma.direccionUsuario, isDireccion, true, 'Corrija Dirección')
		&& checkField(forma.emailUsuario, isEmail, false, 'Corrija Email')
		&& checkField(forma.fonoUsuario, isPhoneNumber, false, 'Corrija Teléfono')
		&& checkField(forma.cargoUsuario, isName, true, 'Corrija Cargo')) {

			if (VerificaRut(forma.rutUsuario, forma.dgvUsuario, 'Corrija Rut')) {
			    forma.submit();
			}
    }
    return false;
}

function actualizarUsuario(forma) {
	if (checkField(forma.nombreCompleto, isName, false, 'Corrija Nombre')
		&& checkField(forma.direccionUsuario, isDireccion, true, 'Corrija Dirección')
		&& checkField(forma.emailUsuario, isEmail, false, 'Corrija Email')
		&& checkField(forma.fonoUsuario, isPhoneNumber, false, 'Corrija Teléfono')
		&& checkField(forma.cargoUsuario, isName, true, 'Corrija Cargo')) {

			if (VerificaRut(forma.rutUsuario, forma.dgvUsuario, 'Corrija Rut')) {
			    forma.submit();
			}
    }
    return false;
}


function agregarSup(f) {
	var idRolSup = f.idRolSup[f.idRolSup.selectedIndex].value;
	if (idRolSup == 0) {
		alert('Escoja Rol de supervision');
		return false;
	}
	var idUsuSup = f.idUsuSup.value;
	if (idUsuSup == '') {
		alert('Escoja Usuario Supervisor');
		return false;
	}

	f.submit();
}

function eliminarSup(idUsuario, idRol) {
	if (confirm('Esta seguro que desea eliminar el registro?')) {
		var f = document.forma;
		f.idUsuarioJerEliminar.value = idUsuario;
		f.idRolJerEliminar.value = idRol;
		f.submit();
	}
	return false;
}

function abreBusquedaUsuarios(accion, idUsuario) {
	var f = document.forma;
	var idRolSup = f.idRolSup[f.idRolSup.selectedIndex].value;
	if (idRolSup == 0) {
		alert('Escoja Rol de Supervision');
		return false;
	}
	var url = 'mantenedorUsuarios.acc?accion=' + accion;
	url += '&idRolSup=' + idRolSup + '&usernameBusqueda=' + f.usernameBusqueda.value;
	url += '&idUsuSub=' + idUsuario;
	popup(url, 0, 0, 'Buscador_Supervisores');
	return false;
}


function limpiaUsuario(forma) {
	forma.idUsuSup.value='';
	forma.usernameBusqueda.value='';
}
