package com.telefonica_chile.bandeja.web.acciones.claves;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.web.MensajesWeb;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.bintegrada.session.UsuarioNoEncontradoException;

/**
 * Input: direccion de correo + nombre de usuario. Si no se encuentra el usuario en la BD ==> error
 * Se genera una clave nueva, la que se actualiza en LDAP y se envia por correo al usuario.
 *
 */
public class RecuperarClaveAcc extends Accion {
	public static final int FORMULARIO = 1;
	public static final int GRABAR_CAMBIOS = 2;
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
		int accion = FORMULARIO;
		try { accion = Integer.parseInt(request.getParameter("accion")); } catch (Exception e) { }

		ejecutarAccion(request, accion);
	}

	public void ejecutarAccion(HttpServletRequest request, int accion) throws Evento {
		String resultado = null;
		switch (accion) {
			case FORMULARIO:
			default:
				resultado = "formNuevaClave";
				break;
			case GRABAR_CAMBIOS:
				resultado = grabarCambios(request);
				break;
		}
		if (resultado != null)
			setResultado(resultado);
	}

	public String grabarCambios(HttpServletRequest request) throws Evento {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		MensajesWeb mensajes = MensajesWeb.getInstance(request);
		if (username != null || email != null) {
			try {
				if (generarNuevaClave(username, email)) {
					mensajes.addOk("Se gener&oacute; una clave nueva, la que fue enviada a " + email);
				} else {
					mensajes.addError("Se encontraron problemas al tratar de generar una cleva nueva. Intente denuevo");
				}
			} catch (UsuarioNoEncontradoException e) {
				mensajes.addError("Usuario o correo no encontrados. Intente denuevo: " + e.getMessage());
			} catch (Exception e) {
				mensajes.addError("Se encontraron problemas al tratar de generar una cleva nueva. Intente denuevo: " + e.getMessage());
			}
		} else {
			mensajes.addError("Debe ingresar correctamente nombre de usuario y correo");
		}
		if (mensajes.hayErrores())
			return "formNuevaClave";
		else
			throw new Evento("mensajesUsuario");
	}
	
	private boolean generarNuevaClave(String username, String correo) throws BandejaException, UsuarioNoEncontradoException {
		BIntegradaSessionLocal bandeja = getBandejaSession();
		return bandeja.nuevaClave(username, correo);
	}

	private BIntegradaSessionLocal getBandejaSession() throws BandejaException {
		try {
			BIntegradaSessionLocalHome home = (BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
			BIntegradaSessionLocal local = home.create();
			return local;
		} catch (Exception e) {
			log.error("Problemas creando bandeja session: " + BIntegradaSessionLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas con servicio de cambio de clave", e);
		}
	}
}
