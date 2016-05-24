package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Dslam_conec2_apsc
 */
public interface Dslam_conec2_apscLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Dslam_conec2_apsc
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Dslam_conec2_apscLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Dslam_conec2_apscLocal create(
		java.lang.Long id_dslam)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Dslam_conec2_apsc
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Dslam_conec2_apscLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Dslam_conec2_apscKey primaryKey)
		throws javax.ejb.FinderException;
}
