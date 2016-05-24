package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_estado
 */
public interface Mensaje_estadoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_estado
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_estadoLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal create(
		java.lang.Integer cod_estado)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_estado
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estadoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey primaryKey)
		throws javax.ejb.FinderException;
}
