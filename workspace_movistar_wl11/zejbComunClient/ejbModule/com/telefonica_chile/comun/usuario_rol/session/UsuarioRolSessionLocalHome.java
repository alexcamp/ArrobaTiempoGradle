package com.telefonica_chile.comun.usuario_rol.session;
/**
 * Local Home interface for Enterprise Bean: UsuarioRolSession
 */
public interface UsuarioRolSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/usuario_rol/session/UsuarioRolSessionLocalHome";
	     
	/**
	 * Creates a default instance of Session Bean: UsuarioRolSession
	 */
	public com.telefonica_chile.comun.usuario_rol.session
		.UsuarioRolSessionLocal create()
		throws javax.ejb.CreateException;
}
