package com.telefonica_chile.vpistbba.session.reinyeccion_mensaje;
/**
 * Local Home interface for Enterprise Bean: ReinyeccionMensaje
 */
public interface ReinyeccionMensajeLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/session/reinyeccion_mensaje/ReinyeccionMensajeLocalHome";
	/**
	 * Creates a default instance of Session Bean: ReinyeccionMensaje
	 */
	public com
		.telefonica_chile
		.vpistbba
		.session
		.reinyeccion_mensaje
		.ReinyeccionMensajeLocal create()
		throws javax.ejb.CreateException;
}
