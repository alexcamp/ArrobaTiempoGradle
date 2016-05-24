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

public class InsercionUsuario {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	private HttpServletRequest request;
	private MantenedoresSessionLocal mantenedores;
	private UtilRequest req = new UtilRequest(request);

	public InsercionUsuario(HttpServletRequest request, MantenedoresSessionLocal mantenedores) {
		this.request = request;
		this.mantenedores = mantenedores;
		this.req = new UtilRequest(request);
	}
	/**
	 * @return  El resultado que la accion deberia setear (Accion.setResultado()). Nulo si no aplica.
	 */
	public String insertarUsuario() throws ManagerException {
		MensajesWeb msgs = MensajesWeb.getInstance(request);

		UsuarioDTO u = new UsuarioDTO();

		String loginUsuario = req.getParameter("loginUsuario");
		if (loginUsuario.length() == 0 || loginUsuario.length() > 20)
			msgs.addError("Corrija nombre de usuario (alfanumérico, máx: 20 caracteres)");
		u.setUsername(loginUsuario);

		String pswUsuario = req.getParameter("pswUsuario");
		if (pswUsuario.length() == 0 || pswUsuario.length() > 15)
			msgs.addError("Corrija password (alfanumérico, máx: 15 caracteres)");
		u.setPassword(pswUsuario);

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

		if (!msgs.hayErrores()) {
			try {
				u = (UsuarioDTO) mantenedores.insert(UsuarioManager.class, u);
			} catch (Exception e) {
				log.warn(e);
				msgs.addError("Hubo problemas realizando la insercion. Intente denuevo");
			}
		}
		
		
		if (msgs.hayErrores()) {
			return "formNuevoUsuario";
		} else {
			msgs.addOk("Usuario " + u.getUsername() + " agregado con &eacute;xito");
			return new RecuperacionUsuario(request, mantenedores).retrieve(u.getId());
		}
	}
}
