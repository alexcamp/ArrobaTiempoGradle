package com.telefonica_chile.vpistbba.asignacion.session;
/**
 * Home interface for Enterprise Bean: Asignacion
 */
public interface AsignacionHome extends javax.ejb.EJBHome {
	public final static String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/asignacion/session/AsignacionHome";
	
	/**
	 * Creates a default instance of Session Bean: Asignacion
	 */
	public com.telefonica_chile.vpistbba.asignacion.session.Asignacion create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
