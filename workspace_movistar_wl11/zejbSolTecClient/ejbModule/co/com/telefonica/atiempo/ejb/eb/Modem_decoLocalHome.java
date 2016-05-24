package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Modem_deco
 */
public interface Modem_decoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Modem_deco
	 */
	public co.com.telefonica.atiempo.ejb.eb.Modem_decoLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Modem_deco
	 */
	public co.com.telefonica.atiempo.ejb.eb.Modem_decoLocal findByPrimaryKey(
		java.lang.Long primaryKey)
		throws javax.ejb.FinderException;
}
