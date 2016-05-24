package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Operacion_comercial_st
 */
public interface Operacion_comercial_stLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Operacion_comercial_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Operacion_comercial_stLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Operacion_comercial_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Operacion_comercial_stLocal create(java.lang.Long opco_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Operacion_comercial_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Operacion_comercial_stLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Operacion_comercial_stKey primaryKey)
		throws javax.ejb.FinderException;
}
