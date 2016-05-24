package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Regla
 */
public interface ReglaLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/ReglaLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Regla
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ReglaLocal create(
		java.lang.Long id_regla)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Regla
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ReglaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.ReglaKey primaryKey)
		throws javax.ejb.FinderException;
}
