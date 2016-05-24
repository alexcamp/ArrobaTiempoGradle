package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mapeo_loc_apsc
 */
public interface Mapeo_loc_apscLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mapeo_loc_apscLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Mapeo_loc_apsc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscLocal create(
		java.lang.Long cod_loc,
		java.lang.String nom_sub_loc) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mapeo_loc_apsc
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscKey primaryKey)
		throws javax.ejb.FinderException;
}
