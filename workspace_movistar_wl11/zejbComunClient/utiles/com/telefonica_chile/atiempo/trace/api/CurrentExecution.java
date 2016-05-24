/*
 * Created on Jul 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface CurrentExecution {
	public void setColumn(Short columnId, Object value);
	public Object getColumn(Short columnId);
	/**
	 * Inicializa la ejecución con información de usuario (login, ip, etc) de la http session
	 */
	public void init(HttpServletRequest request);
	/**
	 * Inicializa la ejecución con información de usuario (login, ip, etc) de la http session
	 */
	public void init(HttpSession httpSession);
	
	public void init();
}
