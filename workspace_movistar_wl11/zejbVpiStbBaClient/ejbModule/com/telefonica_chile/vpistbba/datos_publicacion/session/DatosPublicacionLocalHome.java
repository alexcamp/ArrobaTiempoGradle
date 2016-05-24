package com.telefonica_chile.vpistbba.datos_publicacion.session;
/**
 * Local Home interface for Enterprise Bean: DatosPublicacion
 */
public interface DatosPublicacionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/datos_publicacion/session/DatosPublicacionLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: DatosPublicacion
	 */
	public com
		.telefonica_chile
		.vpistbba
		.datos_publicacion
		.session
		.DatosPublicacionLocal create()
		throws javax.ejb.CreateException;
}
