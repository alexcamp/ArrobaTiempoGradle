/*
 * Created on Dec 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.soltec.grabacion;

import javax.servlet.http.HttpServletRequest;

/**
 * @author caubill
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface GrabadorSt {
	/**
	 * 
	 * @param request
	 * grabar debe modificar los datos de negocio de la BD, eliminar
	 * la tarea de la bandeja integrada y enviar mensaje de fin de 
	 * actividad al workflow
	 */
	void grabar(HttpServletRequest request);
	void grabarSinTerminar(HttpServletRequest request);
	void derivar(HttpServletRequest request);
	HttpServletRequest getRequest();
}
