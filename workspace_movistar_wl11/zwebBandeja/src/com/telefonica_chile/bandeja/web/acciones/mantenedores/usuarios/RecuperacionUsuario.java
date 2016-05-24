package com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.web.MensajesWeb;
import com.tecnonautica.utiles.web.UtilRequest;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.CampoDTO;
import com.telefonica_chile.bandeja.dto.PerfilDTO;
import com.telefonica_chile.bandeja.dto.RolDTO;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;
import com.telefonica_chile.bandeja.mantenedores.session.MantenedoresSessionLocal;
import com.telefonica_chile.bandeja.mantenedores.usuarios.CampoVariableManager;
import com.telefonica_chile.bandeja.mantenedores.usuarios.PerfilManager;
import com.telefonica_chile.bandeja.mantenedores.usuarios.RolManager;
import com.telefonica_chile.bandeja.mantenedores.usuarios.UsuarioManager;

public class RecuperacionUsuario {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private HttpServletRequest request;
	private UtilRequest req;
	private MantenedoresSessionLocal mantenedores;

	public RecuperacionUsuario(HttpServletRequest request, MantenedoresSessionLocal mantenedores) {
		this.request = request;
		this.req = new UtilRequest(request);
		this.mantenedores = mantenedores;
	}
	
	public String retrieve() throws ManagerException {
		MensajesWeb msgs = MensajesWeb.getInstance(request);
		Long idUsuario = null;
		try {
			idUsuario = new Long(req.getParameter("idUsuario"));
		} catch (Exception e) {
			msgs.addError("Id. de Usuario incorrecto: " + req.getParameter("idUsuario"));
			return new ListadoUsuarios(request, mantenedores).listarUsuarios();
		}
		return retrieve(idUsuario);
	}

	public String retrieve(Long idUsuario) throws ManagerException {
		MensajesWeb msgs = MensajesWeb.getInstance(request);
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(idUsuario);
		String resultado = null;
		try {
			usuario = (UsuarioDTO) mantenedores.retrieve(UsuarioManager.class, usuario);
			request.setAttribute("u", usuario);

			List rolesNoAsociados = getRolesNoAsociados(usuario);
			request.setAttribute("rolesNoAsociados", rolesNoAsociados);

			List perfilesNoAsociados = getPerfilesNoAsociados(usuario);
			request.setAttribute("perfilesNoAsociados", perfilesNoAsociados);
			
			List camposNoAsociados = getCamposNoAsociados(usuario);
			request.setAttribute("camposNoAsociados", camposNoAsociados);
			
			resultado = "formEditarUsuario";
		} catch (Exception e) {
			log.error("Hubo problemas recuperando usuario " + idUsuario, e);
			msgs.addError("Hubo problemas recuperando usuario " + idUsuario);
			resultado = new ListadoUsuarios(request, mantenedores).listarUsuarios();
		}

		return resultado;
	}

	private List getRolesNoAsociados(UsuarioDTO usuario) throws ManagerException {
		List completa = mantenedores.list(RolManager.class, new HashMap());
		List asociados = usuario.getRoles();
		
		HashMap mapAsociados = new HashMap();
		for (Iterator it = asociados.iterator(); it.hasNext(); ) {
			RolDTO rol = (RolDTO) it.next();
			mapAsociados.put(rol.getId(), rol);
		}
		List salida = new ArrayList();
		for (Iterator it = completa.iterator(); it.hasNext(); ) {
			RolDTO rol = (RolDTO) it.next();
			if (! mapAsociados.containsKey(rol.getId()))
				salida.add(rol);
		}

		return salida;
	}

	private List getPerfilesNoAsociados(UsuarioDTO usuario) throws ManagerException {
		List completa = mantenedores.list(PerfilManager.class, new HashMap());
		List asociados = usuario.getPerfiles();
		
		HashMap mapAsociados = new HashMap();
		for (Iterator it = asociados.iterator(); it.hasNext(); ) {
			PerfilDTO perfil = (PerfilDTO) it.next();
			mapAsociados.put(perfil.getId(), perfil);
		}
		List salida = new ArrayList();
		for (Iterator it = completa.iterator(); it.hasNext(); ) {
			PerfilDTO perfil = (PerfilDTO) it.next();
			if (! mapAsociados.containsKey(perfil.getId()))
				salida.add(perfil);
		}

		return salida;
	}

	private List getCamposNoAsociados(UsuarioDTO usuario) throws ManagerException {
		List completa = mantenedores.list(CampoVariableManager.class, new HashMap());
		List asociados = usuario.getCamposVariables();
		
		HashMap mapAsociados = new HashMap();
		for (Iterator it = asociados.iterator(); it.hasNext(); ) {
			CampoDTO campo = (CampoDTO) it.next();
			mapAsociados.put(new Short(campo.getId()), campo);
		}
		List salida = new ArrayList();
		for (Iterator it = completa.iterator(); it.hasNext(); ) {
			CampoDTO campo = (CampoDTO) it.next();
			if (! mapAsociados.containsKey(new Short(campo.getId())))
				salida.add(campo);
		}

		return salida;
	}
}
