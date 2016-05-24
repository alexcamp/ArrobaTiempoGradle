package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Familia_producto_servicio_st
 */
public interface Familia_producto_servicio_stLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Familia_producto_servicio_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Familia_producto_servicio_stLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Familia_producto_servicio_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal create(java.lang.Long faps_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Familia_producto_servicio_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Familia_producto_servicio_stKey primaryKey)
		throws javax.ejb.FinderException;
}
