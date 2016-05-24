package com.telefonica_chile.vpistbba.datos_publicacion.session;
/**
 * Home interface for Enterprise Bean: DatosPublicacion
 */
public interface DatosPublicacionHome extends javax.ejb.EJBHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/datos_publicacion/session/DatosPublicacionHome";
	/**
	 * Creates a default instance of Session Bean: DatosPublicacion
	 */
	public com.telefonica_chile.vpistbba.datos_publicacion.session.DatosPublicacion create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
