package com.telefonica_chile.bandeja.usuariossupervisadossession;
/**
 * Local Home interface for Enterprise Bean: UsuariosSupervisadosSession
 */
public interface UsuariosSupervisadosSessionLocalHome extends javax.ejb.EJBLocalHome {
		
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/bandeja/usuariossupervisadossession/UsuariosSupervisadosSessionHome";	
	/**
	 * Creates a default instance of Session Bean: UsuariosSupervisadosSession
	 */
	public com
		.telefonica_chile
		.bandeja
		.usuariossupervisadossession
		.UsuariosSupervisadosSessionLocal create()
		throws javax.ejb.CreateException;
}
