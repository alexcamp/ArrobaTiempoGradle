package com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.web.MensajesWeb;
import com.tecnonautica.utiles.web.UtilRequest;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;
import com.telefonica_chile.bandeja.mantenedores.session.MantenedoresSessionLocal;
import com.telefonica_chile.bandeja.mantenedores.usuarios.UsuarioManager;

public class ActualizacionUsuario {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private HttpServletRequest request;
	private UtilRequest req;
	private MantenedoresSessionLocal mantenedores;

	public ActualizacionUsuario(HttpServletRequest request, MantenedoresSessionLocal mantenedores) {
		this.request = request;
		this.req = new UtilRequest(request);
		this.mantenedores = mantenedores;
	}

	public String actualizarUsuario() throws ManagerException {

		MensajesWeb msgs = MensajesWeb.getInstance(request);

		UsuarioDTO u = new UsuarioDTO();
		Long idUsuario = null;
		try {
			idUsuario = new Long(req.getParameter("idUsuario"));
			u.setId(idUsuario);
		} catch (Exception e) {
			msgs.addError("Id. de Usuario incorrecto");
		}

		String nombreCompleto = req.getParameter("nombreCompleto");
		if (nombreCompleto.length() == 0 || nombreCompleto.length() > 15)
			msgs.addError("Corrija nombre completo (alfanumérico, máx: 80 caracteres)");
		u.setNombre(nombreCompleto);

		String direccionUsuario = req.getParameter("direccionUsuario");
		if (direccionUsuario.length() > 300)
			msgs.addError("Corrija direccion (alfanumérico, máx: 300 caracteres)");
		u.setDireccion(direccionUsuario);

		String fonoUsuario = req.getParameter("fonoUsuario");
		if (fonoUsuario.length() == 0 || fonoUsuario.length() > 12)
			msgs.addError("Corrija tel&acute;fono (alfanumérico, máx: 12 caracteres)");
		u.setFono(fonoUsuario);

		String emailUsuario = req.getParameter("emailUsuario");
		if (emailUsuario.length() == 0 || emailUsuario.length() > 300)
			msgs.addError("Corrija email usuario (xx@yy.zz, máx: 300 caracteres)");
		u.setEmail(emailUsuario);

		String rut = req.getParameter("rutUsuario");
		String dv = req.getParameter("dgvUsuario");
		if (rut.length() == 0 || dv.length() == 0)
			msgs.addError("Corrija RUT");
		u.setRut(rut + "-" + dv);

		String cargo = req.getParameter("cargoUsuario");
		if (cargo.length() > 50)
			msgs.addError("Corrija Cargo (alfanumérico, máx: 50 caracteres)");
		u.setCargo(cargo);

		try { u.setRolPorAgregar(new Long(req.getParameter("idRol"))); } catch (Exception e) { }
		try { u.setPerfilPorAgregar(new Long(req.getParameter("idPerfil"))); } catch (Exception e) { }
		try { u.setCampoPorAgregar(Short.parseShort(req.getParameter("idCampo")));  } catch (Exception e) { }
		try { u.setRolPorEliminar(new Long(req.getParameter("idRolEliminar"))); } catch (Exception e) { }
		try { u.setPerfilPorEliminar(new Long(req.getParameter("idPerfilEliminar"))); } catch (Exception e) {}
		try { u.setCampoPorEliminar(Short.parseShort(req.getParameter("idCampoEliminar"))); } catch (Exception e) {}

		if ("S".equals(req.getParameter("habilitado")))
			u.setHabilitado("S");

		// Agregado de supervisor
		try {
			Long parAgregarIdUsuario = new Long(req.getParameter("idUsuSup"));
			Long parAgregarIdRol = new Long(req.getParameter("idRolSup"));
			u.setParSupervisorPorAgregar(parAgregarIdUsuario, parAgregarIdRol);
		} catch (Exception e) {
		}
		
		// Eliminado de supervisor
		try {
			Long parEliminarIdUsuario = new Long(req.getParameter("idUsuarioJerEliminar"));
			Long parEliminarIdRol = new Long(req.getParameter("idRolJerEliminar"));
			u.setParSupervisorPorEliminar(parEliminarIdUsuario, parEliminarIdRol);
		} catch (Exception e) {
		}

		if (!msgs.hayErrores()) {
			try {
				mantenedores.update(UsuarioManager.class, u);
			} catch (Exception e) {
				log.warn("Hubo problemas realizando la actualizacion", e);
				msgs.addError("Hubo problemas realizando la actualizacion. Intente denuevo");
			}
		}
		
		// Todo ok ==> Voy a editar denuevo con mensaje ok
		if (!msgs.hayErrores()) {
			msgs.addOk("Usuario actualizado con &eacute;xito");
		}
			
		return new RecuperacionUsuario(request, mantenedores).retrieve();
	}
}
