package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Altapctv
 */
public interface AltapctvLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Altapctv
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/AltapctvLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Altapctv
	 */
	public co.com.telefonica.atiempo.ejb.eb.AltapctvLocal create(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Altapctv
	 */
	public co.com.telefonica.atiempo.ejb.eb.AltapctvLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.AltapctvKey primaryKey)
		throws javax.ejb.FinderException;
}
