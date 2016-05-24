/*
 * Created on Jul 31, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.trace.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceManager;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CurrentExecutionImpl implements CurrentExecution {

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#setColumn(java.lang.Short, java.lang.Object)
	 */
	private String ipUsuario = "";
	private String usuario = "";
	private String petAtiempo = "";
	private String root = "";
	private Long transPrincipal = null;
	private String server = "";
	
	public void setColumn(Short columnId, Object value) {
		if(value == null)
			return;
		
		if(TraceManager.COL_USER_IP.shortValue() == columnId.shortValue())
		   ipUsuario = value.toString();
		   
		if(TraceManager.COL_USER_LOGIN.shortValue() == columnId.shortValue())
		   usuario = value.toString();
		   
		if(TraceManager.COL_OP_NAME.shortValue() == columnId.shortValue())
		   petAtiempo = value.toString();
		   
		if(TraceManager.COL_OP_PRINCIPAL.shortValue() == columnId.shortValue())
			root = value.toString();
		if(TraceManager.COL_TRANS_PRINCIPAL.shortValue() == columnId.shortValue())
			transPrincipal = (Long) value;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#getColumn(java.lang.Short)
	 */
	public Object getColumn(Short columnId) {
		
		if(TraceManager.COL_USER_IP.shortValue() == columnId.shortValue())
		   return ipUsuario;
		   
		if(TraceManager.COL_USER_LOGIN.shortValue() == columnId.shortValue())
		   return usuario;
		   
		if(TraceManager.COL_NUM_PET_ATIEMPO.shortValue() == columnId.shortValue())
		   return petAtiempo;
		if(TraceManager.COL_OP_PRINCIPAL.shortValue() == columnId.shortValue())
			return root;
		if(TraceManager.COL_TRANS_PRINCIPAL.shortValue() == columnId.shortValue())
			return transPrincipal;
		if(TraceManager.COL_SERVER_APPLICATION.shortValue() == columnId.shortValue())
			return server;
		
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#init(javax.servlet.http.HttpServletRequest)
	 */
	public void init(HttpServletRequest request) {
		ipUsuario =	request.getRemoteHost();				
		if(request.getUserPrincipal()!=null)
		   usuario = request.getUserPrincipal().getName();
		petAtiempo = (String) request.getAttribute("folio");
		
		javax.servlet.ServletContext sc = request.getSession().getServletContext();
	  	server = sc.getAttribute("com.ibm.websphere.servlet.application.host").toString();
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#init(javax.servlet.http.HttpSession)
	 */
	public void init(HttpSession httpSession) {		

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution#init()
	 */
	public void init() {
		
		
	}
}
