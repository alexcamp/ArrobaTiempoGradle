package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Recursos_ba
 */
public interface Recursos_baLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_ba
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Recursos_baLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Recursos_ba
	 */
	public co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal create(
		java.lang.Long id_conector)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Recursos_ba
	 */
	public co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Recursos_baKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal findbyPeti_numero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}
