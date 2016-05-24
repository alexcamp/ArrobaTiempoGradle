package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Excepcion_rango
 */
public interface Excepcion_rangoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Excepcion_rango
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Excepcion_rangoLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoLocal create(
		java.lang.Long exra_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Excepcion_rango
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Excepcion_rangoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Excepcion_rango
	 */
	public co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoLocal create(
		java.lang.Long exra_id,
		java.lang.Integer id_rango,
		java.lang.String codigo_pcom)
		throws javax.ejb.CreateException;
}
