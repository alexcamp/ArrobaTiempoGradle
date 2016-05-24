package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Homologacion_qb_Agd_sc
 */
public interface Homologacion_qb_Agd_scLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Homologacion_qb_Agd_sc
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Homologacion_qb_Agd_scLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scLocal create(
		java.lang.String hq_agd_cod_familia) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Homologacion_qb_Agd_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scKey primaryKey)
		throws javax.ejb.FinderException;
}
