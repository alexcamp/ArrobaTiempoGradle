package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Conector
 */
public interface ConectorLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Conector
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ConectorLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.ConectorLocal create(
		java.lang.Integer cod_conector)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Conector
	 */
	public co.com.telefonica.atiempo.ejb.eb.ConectorLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ConectorKey primaryKey)
		throws javax.ejb.FinderException;
}
