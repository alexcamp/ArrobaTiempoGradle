package com.telefonica_chile.comun.usuario.session;
/**
 * Local Home interface for Enterprise Bean: UsuarioSession
 */
public interface UsuarioSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/usuario/session/UsuarioSessionLocalHome";

	/**
	 * Creates a default instance of Session Bean: UsuarioSession
	 */
	public com
		.telefonica_chile
		.comun
		.usuario
		.session
		.UsuarioSessionLocal create()
		throws javax.ejb.CreateException;
}
