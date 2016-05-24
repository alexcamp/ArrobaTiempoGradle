package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Subtipo_pc
 */
public interface Subtipo_pcLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Subtipo_pc
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Subtipo_pcLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Subtipo_pc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Subtipo_pcLocal create(
		java.lang.Integer id_subtipo_pc)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Subtipo_pc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Subtipo_pcLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Subtipo_pcKey primaryKey)
		throws javax.ejb.FinderException;
}
