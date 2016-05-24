package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Area_gestion
 */
public interface Area_gestionLocalHome extends javax.ejb.EJBLocalHome {

	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Area_gestionLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Area_gestion
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Area_gestionLocal create(
		java.lang.Long id_area_ges)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Area_gestion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Area_gestionLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.Area_gestionKey primaryKey)
		throws javax.ejb.FinderException;
}
