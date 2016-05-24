package com.telefonica_chile.bandeja.web.seguridad;

import javax.servlet.ServletRequest;

import com.tecnonautica.seguridad.ControladorDeRecurso;

/**
 * Controlador asociado a recursos que no requieren
 * que el usuario este autenticado.
 */
public class ControladorDeSeguridadTrue extends ControladorDeRecurso {

	/**
	 * @return true Siempre
	 */
	public boolean autoriza(String recurso, ServletRequest request) {
		return true;
	}
}
