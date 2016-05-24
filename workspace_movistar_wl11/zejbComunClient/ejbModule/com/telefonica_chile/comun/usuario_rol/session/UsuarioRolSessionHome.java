package com.telefonica_chile.comun.usuario_rol.session;
/**
 * Home interface for Enterprise Bean: UsuarioRolSession
 */
public interface UsuarioRolSessionHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: UsuarioRolSession
	 */
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/vpistbba/usuario_rol/session/UsuarioRolSessionHome";
	
	public com.telefonica_chile.comun.usuario_rol.session.UsuarioRolSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
