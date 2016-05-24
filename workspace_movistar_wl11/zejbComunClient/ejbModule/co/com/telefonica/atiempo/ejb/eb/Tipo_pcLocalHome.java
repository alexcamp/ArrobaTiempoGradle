package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_pc
 */
public interface Tipo_pcLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_pc
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Tipo_pcLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_pc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal create(
		java.lang.Integer id_tipo_pc)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_pc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tipo_pcKey primaryKey)
		throws javax.ejb.FinderException;
}
