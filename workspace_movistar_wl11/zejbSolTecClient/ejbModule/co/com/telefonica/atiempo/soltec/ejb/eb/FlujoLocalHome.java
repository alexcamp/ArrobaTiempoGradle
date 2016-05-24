package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Flujo
 */
public interface FlujoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Flujo
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/FlujoLocalHome";
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal create(
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Flujo
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Flujo
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal create(
		java.lang.Integer fluj_id,
		java.lang.String fluj_codigo)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
