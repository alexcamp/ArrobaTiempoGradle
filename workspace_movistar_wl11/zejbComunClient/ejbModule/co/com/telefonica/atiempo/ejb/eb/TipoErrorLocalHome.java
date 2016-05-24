package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: TipoError
 */
public interface TipoErrorLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: TipoError
	 */
	public co.com.telefonica.atiempo.ejb.eb.TipoErrorLocal create(
		java.lang.Long id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: TipoError
	 */
	public co.com.telefonica.atiempo.ejb.eb.TipoErrorLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.TipoErrorKey primaryKey)
		throws javax.ejb.FinderException;
}
