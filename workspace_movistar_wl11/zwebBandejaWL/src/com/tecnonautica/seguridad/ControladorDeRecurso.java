package com.tecnonautica.seguridad;

import javax.servlet.ServletRequest;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class ControladorDeRecurso {
	public abstract boolean autoriza(String recurso, ServletRequest request);
}
