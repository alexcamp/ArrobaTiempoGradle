/*
 * Created on Mar 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;

public class BuscarClienteAcc extends Accion {
	private Logger log = Logger.getLogger(BuscarClienteAcc.class);

	protected void ejecutar(HttpServletRequest request) throws Evento {
		log.debug("Redireccionando a buscar cliente");
	}

}
