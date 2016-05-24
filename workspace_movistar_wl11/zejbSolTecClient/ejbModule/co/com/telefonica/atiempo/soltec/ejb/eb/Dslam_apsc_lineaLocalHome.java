package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Dslam_apsc_linea
 */
public interface Dslam_apsc_lineaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Dslam_apsc_linea
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Dslam_apsc_lineaLocalHome";
	
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Dslam_apsc_lineaLocal create(
		java.lang.Long id_dslam)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Dslam_apsc_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Dslam_apsc_lineaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Dslam_apsc_lineaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Dslam_apsc_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Dslam_apsc_lineaLocal create()
		throws javax.ejb.CreateException;
		
	public java.util.Collection findConector(java.lang.Long idconector) throws javax.ejb.FinderException;
}
