package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Ps_rol
 */
public interface Ps_rolLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Ps_rolLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Ps_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ps_rolLocal create(
		java.lang.Long ps_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Ps_rol
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ps_rolLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Ps_rolKey primaryKey)
		throws javax.ejb.FinderException;
}