package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Dslam_conec6_apsc_linea
 */
public interface Dslam_conec6_apsc_lineaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Dslam_conec6_apsc_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Dslam_conec6_apsc_lineaLocal create(
		java.lang.Long id_dslam)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Dslam_conec6_apsc_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Dslam_conec6_apsc_lineaLocal findByPrimaryKey(
			java.lang.Long primaryKey)
		throws javax.ejb.FinderException;
}
