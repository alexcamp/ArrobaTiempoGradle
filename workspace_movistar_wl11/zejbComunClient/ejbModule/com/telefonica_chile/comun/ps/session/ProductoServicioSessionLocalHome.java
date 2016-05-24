package com.telefonica_chile.comun.ps.session;
/**
 * Local Home interface for Enterprise Bean: ProductoServicioSession
 */
public interface ProductoServicioSessionLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/com/telefonica_chile/comun/ps/session/ProductoServicioSessionLocalHome";

	/**
	 * Creates a default instance of Session Bean: ProductoServicioSession
	 */
	public com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocal create() throws javax.ejb.CreateException;
}
