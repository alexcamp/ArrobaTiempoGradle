package com.telefonica_chile.bandeja.datos.usuarios;
/**
 * Home interface for Enterprise Bean: UsuariosSession
 */
public interface UsuariosSessionHome extends javax.ejb.EJBHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/datos/usuarios/UsuariosSessionHome";
	/**
	 * Creates a default instance of Session Bean: UsuariosSession
	 */
	public com.telefonica_chile.bandeja.datos.usuarios.UsuariosSession create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
