package com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.web.UtilRequest;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;
import com.telefonica_chile.bandeja.mantenedores.session.MantenedoresSessionLocal;
import com.telefonica_chile.bandeja.mantenedores.session.MantenedoresSessionLocalHome;

public class MantenedorUsuariosAcc extends Accion {
	public static final int ACCION_LISTAR = 0;
	public static final int ACCION_INSERTAR = 1;
	public static final int ACCION_ACTUALIZAR = 2;
	public static final int ACCION_ELIMINAR = 3;
	public static final int ACCION_FORMULARIO_NUEVO_USUARIO = 4;
	public static final int ACCION_FORMULARIO_EDITAR_USUARIO = 5;
	public static final int ACCION_BUSCAR_USUARIO_SUPERVISOR = 6;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private UtilRequest req;

	protected void ejecutar(HttpServletRequest request) throws Evento {
		this.req = new UtilRequest(request);
		int accion = ACCION_LISTAR;
		try { accion = Integer.parseInt(request.getParameter("accion")); } catch (Exception e) { }

		try {
			execute(request, accion);
		} catch (ManagerException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
	}
	private void execute(HttpServletRequest request, int accion) throws ManagerException {
		
		MantenedoresSessionLocal mantenedores = getMantenedoresSession();

		String resultado = null;
		switch(accion) {
			case ACCION_LISTAR:
			default:
				resultado = new ListadoUsuarios(request, mantenedores).listarUsuarios();
				break;
			case ACCION_FORMULARIO_NUEVO_USUARIO:
				resultado = "formNuevoUsuario";
				break;
			case ACCION_INSERTAR:
				resultado = new InsercionUsuario(request, mantenedores).insertarUsuario();
				break;
			case ACCION_ACTUALIZAR:
				resultado = new ActualizacionUsuario(request, mantenedores).actualizarUsuario();
				break;
			case ACCION_FORMULARIO_EDITAR_USUARIO:
				resultado = new RecuperacionUsuario(request, mantenedores).retrieve();
				break;
			case ACCION_BUSCAR_USUARIO_SUPERVISOR:
				resultado = new ListadoUsuarios(request, mantenedores).buscarSupervisores();
				break;
		}

		if (resultado != null)
			setResultado(resultado);
	}
	private MantenedoresSessionLocal getMantenedoresSession() throws ManagerException {
		try {
			MantenedoresSessionLocalHome home =
					(MantenedoresSessionLocalHome)HomeFactory.getHome(MantenedoresSessionLocalHome.JNDI_NAME);
			MantenedoresSessionLocal local = home.create();
			return local;
		} catch (Exception e) {
			log.error("Problemas creando session bean mantenedores", e);
			throw new ManagerException("Problemas con los mantenedores", e);
		}
	}
}
