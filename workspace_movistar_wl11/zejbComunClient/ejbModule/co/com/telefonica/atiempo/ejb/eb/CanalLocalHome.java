package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Canal
 */
public interface CanalLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/CanalLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Canal
	 */
	public co.com.telefonica.atiempo.ejb.eb.CanalLocal create(
		java.lang.Long cod_cnl_ven_cd)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Canal
	 */
	public co.com.telefonica.atiempo.ejb.eb.CanalLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.CanalKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
