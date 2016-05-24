package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Properties_BD
 */
public interface Properties_BDLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Properties_BDLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Properties_BD
	 */
	public co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal create(
		java.lang.String codigo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Properties_BD
	 */
	public co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal findByPrimaryKey(
		java.lang.String primaryKey) throws javax.ejb.FinderException;
}