package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Flujo
 */
public interface FlujoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Flujo
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/FlujoLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.FlujoLocal create(
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Flujo
	 */
	public co.com.telefonica.atiempo.ejb.eb.FlujoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.FlujoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Flujo
	 */
	public co.com.telefonica.atiempo.ejb.eb.FlujoLocal create(
		java.lang.Integer fluj_id,
		java.lang.String fluj_codigo)
		throws javax.ejb.CreateException;
}
