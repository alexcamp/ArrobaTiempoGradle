package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Producto_servicio_st
 */
public interface Producto_servicio_stLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Producto_servicio_stLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_stLocal create(
		java.lang.Long ps_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Producto_servicio_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_stLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Producto_servicio_stKey primaryKey)
		throws javax.ejb.FinderException;
}
