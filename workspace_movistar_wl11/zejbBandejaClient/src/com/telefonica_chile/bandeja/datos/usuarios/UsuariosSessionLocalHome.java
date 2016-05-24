package com.telefonica_chile.bandeja.datos.usuarios;
/**
 * Local Home interface for Enterprise Bean: UsuariosSession
 */
public interface UsuariosSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/datos/usuarios/UsuariosSessionHome";
	
	/**
	 * Creates a default instance of Session Bean: UsuariosSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.datos
		.usuarios
		.UsuariosSessionLocal create()
		throws javax.ejb.CreateException;
}
