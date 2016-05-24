package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Rango
 */
public interface RangoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Rango
	 */
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/RangoLocalHome";

	public co.com.telefonica.atiempo.ejb.eb.RangoLocal create(java.lang.Integer id_rango) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Rango
	 */
	public co.com.telefonica.atiempo.ejb.eb.RangoLocal findByPrimaryKey(co.com.telefonica.atiempo.ejb.eb.RangoKey primaryKey) throws javax.ejb.FinderException;

	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
