package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Central
 */
public interface CentralLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Central
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/CentralLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.CentralLocal create(
		java.lang.Long cod_central)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Central
	 */
	public co.com.telefonica.atiempo.ejb.eb.CentralLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.CentralKey primaryKey)
		throws javax.ejb.FinderException;
}
