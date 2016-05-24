package com.telefonica_chile.soltec.grabacion;
/**
 * Local Home interface for Enterprise Bean: GrabadorSolTec
 */
public interface GrabadorSolTecLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/soltec/grabacion/GrabadorSolTecLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: GrabadorSolTec
	 */
	public com.telefonica_chile.soltec.grabacion.GrabadorSolTecLocal create()
		throws javax.ejb.CreateException;
}
