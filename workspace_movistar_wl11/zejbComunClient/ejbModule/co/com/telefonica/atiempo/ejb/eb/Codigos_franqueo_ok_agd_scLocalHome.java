package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Codigos_franqueo_ok_agd_sc
 */
public interface Codigos_franqueo_ok_agd_scLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Codigos_franqueo_ok_agd_scLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Codigos_franqueo_ok_agd_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocal create(
		java.lang.String codigo_franqueo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Codigos_franqueo_ok_agd_sc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey primaryKey)
		throws javax.ejb.FinderException;
}