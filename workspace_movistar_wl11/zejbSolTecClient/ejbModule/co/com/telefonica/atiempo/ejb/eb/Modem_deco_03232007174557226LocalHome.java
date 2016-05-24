package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Modem_deco_03232007174557226
 */
public interface Modem_deco_03232007174557226LocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Modem_deco_03232007174557226
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Modem_deco_03232007174557226Local create(java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Modem_deco_03232007174557226
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Modem_deco_03232007174557226Local findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Modem_deco_03232007174557226Key primaryKey)
		throws javax.ejb.FinderException;
}
