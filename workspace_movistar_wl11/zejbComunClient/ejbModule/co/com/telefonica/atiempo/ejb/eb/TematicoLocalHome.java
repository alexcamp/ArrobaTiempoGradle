package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tematico
 */
public interface TematicoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tematico
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/TematicoLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Tematico
	 */
	public co.com.telefonica.atiempo.ejb.eb.TematicoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.TematicoKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.TematicoLocal create(
		Long id_tematico,
		String origen,
		PeticionLocal pet,
		String codtematico,
		Long correlativo)
		throws javax.ejb.CreateException;
}
