package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Regla_Retenciones
 */
public interface Regla_RetencionesLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Regla_RetencionesLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Regla_Retenciones
	 */
	public co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal create(
		java.lang.Long regla_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Regla_Retenciones
	 */
	public co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesKey primaryKey)
		throws javax.ejb.FinderException;
	
	public java.util.Collection findByEstado(java.lang.Integer estado)
		throws javax.ejb.FinderException;
}
