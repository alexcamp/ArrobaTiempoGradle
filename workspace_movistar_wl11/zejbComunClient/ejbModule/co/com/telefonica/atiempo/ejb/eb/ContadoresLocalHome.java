package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Contadores
 */
public interface ContadoresLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Contadores
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ContadoresLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Contadores
	 */
	public co.com.telefonica.atiempo.ejb.eb.ContadoresLocal create(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal argOperacion_comercial)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Contadores
	 */
	public co.com.telefonica.atiempo.ejb.eb.ContadoresLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ContadoresKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Contadores
	 */
	public co.com.telefonica.atiempo.ejb.eb.ContadoresLocal create(
		java.lang.Long operacion_comercial_opco_id)
		throws javax.ejb.CreateException;
}
