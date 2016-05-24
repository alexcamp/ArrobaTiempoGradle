package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Sucesos
 */
public interface SucesosLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Sucesos
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/SucesosLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.SucesosLocal create(
		java.lang.Integer id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Sucesos
	 */
	public co.com.telefonica.atiempo.ejb.eb.SucesosLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.SucesosKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}