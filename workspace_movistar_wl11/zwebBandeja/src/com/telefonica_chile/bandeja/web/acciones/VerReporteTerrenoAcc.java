package com.telefonica_chile.bandeja.web.acciones;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;

/**
 * @author Leonardo Caldera
 *
 */
public class VerReporteTerrenoAcc extends Accion
{
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		try
		{
			HttpSession session = request.getSession(true);
			// Recuperamos el usuario de la Bandeja.
			AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion)getControladorDeAplicacion();
			UsuarioWeb usuario = control.getUsuario();
			if ( usuario == null ) {
				log.info("No se encontro usuario en Session.");
			}
			request.setAttribute("usuario", usuario);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
		}
	}
}
