package com.telefonica_chile.bandeja.web.acciones.claves;

import java.io.UnsupportedEncodingException;

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
import com.telefonica_chile.bandeja.bintegrada.session.UsuarioNoAutenticadoException;
import com.telefonica_chile.bandeja.bintegrada.session.UsuarioNoEncontradoException;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;

public class CambiarClaveAcc extends Accion {
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
				resultado = "formCambiarClave";
				break;
			case GRABAR_CAMBIOS:
				resultado = grabarCambios(request);
				break;
		}
		if (resultado != null)
			setResultado(resultado);
	}

	private MensajesWeb mensajes;
	
	public String grabarCambios(HttpServletRequest request) throws Evento {
		String oldClave = request.getParameter("oldClave");
		String newClave = request.getParameter("newClave");
		String newClave2 = request.getParameter("newClave2");
		
		mensajes = MensajesWeb.getInstance(request);
		if (oldClave == null)
			mensajes.addError("Clave antigua no v&acute;lida");
		if (newClave == null)
			mensajes.addError("Clave nueva no v&acute;lida");
		if (newClave2 == null || !newClave2.equals(newClave))
			mensajes.addError("Repetici&oacute;n de clave nueva no v&acute;lida");

		AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion) getControladorDeAplicacion();
		Long idUsuario = control.getUsuario().getId();
		String username=control.getUsuario().getUsername();

		try {
			log.debug("VARIABLES CAMBIO CLAVE: idUsuario["+idUsuario+"], oldClave["+oldClave+"], newClave["+newClave+"]");
			//new ChangePass(username.toLowerCase(),oldClave,newClave);
			cambiarClave(idUsuario, oldClave, newClave);
		} catch (UsuarioNoAutenticadoException e) {
			mensajes.addError("No fue posible autenticar correctamente al usuario: " + e.getMessage());
		} catch (UsuarioNoEncontradoException e) {
			mensajes.addError("No se pudo recuperar usuario: " + e.getMessage());
		} catch (BandejaException e) {
			mensajes.addError("No fue posible cambiar clave correctamente al usuario. Intente m&aacute;s tarde: " + e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			mensajes.addError("No fue posible cambiar clave correctamente al usuario. Intente m&aacute;s tarde: " + e.getMessage());
		}

		if (mensajes.hayErrores()) {
			return "formCambiarClave";
		}
		
		request.setAttribute("cambioClave","Cambio de Clave Exitoso");
		return "informeCambioClave";
		
//		mensajes.addOk("La clave fue cambiada con &eacute;xito");
//		throw new Evento("mensajesUsuario");
	}

	private void cambiarClave(Long idUsuario, String oldClave, String newClave) throws BandejaException, UsuarioNoAutenticadoException, UsuarioNoEncontradoException {
		BIntegradaSessionLocal bandeja = getBandejaSession();
		bandeja.cambiaClaveUsuario(idUsuario, oldClave, newClave);
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
	
	private String encodePassword(String pass) throws UnsupportedEncodingException 
	{
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		final String ATT_ENCODING = "Unicode"; 
		//	   Agree with MS's ATTRIBUTE_CONSTRAINT
		String pwd = "\"" + pass +"\"";
		byte _bytes[] = pwd.getBytes(ATT_ENCODING); 
	//	   strip unicode marker
		byte bytes[] = new byte [_bytes.length - 2];

		System.arraycopy(_bytes, 2, bytes, 0,_bytes.length - 2);

		String base64 = encoder.encode(bytes);

		return base64;
	}


}

