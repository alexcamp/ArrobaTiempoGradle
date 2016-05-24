package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Dslam_conec9_apsc
 */
public interface Dslam_conec9_apscLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Dslam_conec9_apsc
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Dslam_conec9_apscLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Dslam_conec9_apscLocal create(
		java.lang.Long id_dslam)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Dslam_conec9_apsc
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Dslam_conec9_apscLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Dslam_conec9_apscKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByConector(java.lang.Long conector) throws javax.ejb.FinderException;
}
