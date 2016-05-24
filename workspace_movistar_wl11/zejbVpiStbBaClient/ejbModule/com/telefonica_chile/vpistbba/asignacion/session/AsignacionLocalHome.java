package com.telefonica_chile.vpistbba.asignacion.session;
/**
 * Local Home interface for Enterprise Bean: Asignacion
 */
public interface AsignacionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/asignacion/session/AsignacionHome";

	/**
	 * Creates a default instance of Session Bean: Asignacion
	 */
	public com
		.telefonica_chile
		.vpistbba
		.asignacion
		.session
		.AsignacionLocal create()
		throws javax.ejb.CreateException;
}
