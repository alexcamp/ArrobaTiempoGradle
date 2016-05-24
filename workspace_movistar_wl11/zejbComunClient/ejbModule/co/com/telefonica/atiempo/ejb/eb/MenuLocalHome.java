package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Menu
 */
public interface MenuLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Menu
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/MenuLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.MenuLocal create(
		java.lang.Long mn_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Menu
	 */
	public co.com.telefonica.atiempo.ejb.eb.MenuLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.MenuKey primaryKey)
		throws javax.ejb.FinderException;
}
