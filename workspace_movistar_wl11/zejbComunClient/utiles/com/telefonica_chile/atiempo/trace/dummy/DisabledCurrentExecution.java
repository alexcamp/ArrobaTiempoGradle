/*
 * Created on Aug 4, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.trace.dummy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.telefonica_chile.atiempo.trace.api.CurrentExecution;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisabledCurrentExecution implements CurrentExecution {

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#setColumn(java.lang.Short, java.lang.Object)
	 */
	public void setColumn(Short columnId, Object value) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#getColumn(java.lang.Short)
	 */
	public Object getColumn(Short columnId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#init(javax.servlet.http.HttpServletRequest)
	 */
	public void init(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#init(javax.servlet.http.HttpSession)
	 */
	public void init(HttpSession httpSession) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#init()
	 */
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
