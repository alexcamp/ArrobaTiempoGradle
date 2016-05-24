package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Estado_ps
 */
public interface Estado_psLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Estado_ps
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Estado_psLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Estado_psLocal create(
		java.lang.Long cod_estado_cierre)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Estado_ps
	 */
	public co.com.telefonica.atiempo.ejb.eb.Estado_psLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Estado_psKey primaryKey)
		throws javax.ejb.FinderException;
}
