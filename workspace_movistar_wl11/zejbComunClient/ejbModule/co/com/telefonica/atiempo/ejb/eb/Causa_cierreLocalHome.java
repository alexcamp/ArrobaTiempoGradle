package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Causa_cierre
 */
public interface Causa_cierreLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Causa_cierre
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Causa_cierreLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Causa_cierreLocal create(
		java.lang.Long caci_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Causa_cierre
	 */
	public co.com.telefonica.atiempo.ejb.eb.Causa_cierreLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Causa_cierreKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Causa_cierre
	 */
	public co.com.telefonica.atiempo.ejb.eb.Causa_cierreLocal create(
		java.lang.Long caci_id,
		java.lang.Integer ambi_id,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Cierre_actividadLocal argFk_ciac_2_caci,
		co.com.telefonica.atiempo.ejb.eb.CausaLocal argFk_caus_caci)
		throws javax.ejb.CreateException;
	//public java.util.Collection findAllCausasCierre() throws javax.ejb.FinderException;
}
