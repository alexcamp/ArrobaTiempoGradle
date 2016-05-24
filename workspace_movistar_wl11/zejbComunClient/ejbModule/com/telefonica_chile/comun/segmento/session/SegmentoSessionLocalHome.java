package com.telefonica_chile.comun.segmento.session;
/**
 * Local Home interface for Enterprise Bean: SegmentoSession
 */
public interface SegmentoSessionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/segmento/session/SegmentoSessionLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: SegmentoSession
	 */
	public com
		.telefonica_chile
		.comun
		.segmento
		.session
		.SegmentoSessionLocal create()
		throws javax.ejb.CreateException;
}
