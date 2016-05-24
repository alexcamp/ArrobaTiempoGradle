package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Subsegmento
 */
public interface SubsegmentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Subsegmento
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/SubsegmentoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Subsegmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal create(
		java.lang.Long subsegm_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Subsegmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey primaryKey)
		throws javax.ejb.FinderException;
}
