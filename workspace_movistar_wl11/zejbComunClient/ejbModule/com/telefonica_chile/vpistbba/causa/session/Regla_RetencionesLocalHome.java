package com.telefonica_chile.vpistbba.causa.session;
/**
 * Local Home interface for Enterprise Bean: Regla_Retenciones
 */
public interface Regla_RetencionesLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Regla_Retenciones
	 */
	public com.telefonica_chile.vpistbba.causa.session.Regla_RetencionesLocal create(
		java.lang.Long regla_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Regla_Retenciones
	 */
	public com.telefonica_chile.vpistbba.causa.session.Regla_RetencionesLocal findByPrimaryKey(
		com.telefonica_chile.vpistbba.causa.session.Regla_RetencionesKey primaryKey)
		throws javax.ejb.FinderException;
}