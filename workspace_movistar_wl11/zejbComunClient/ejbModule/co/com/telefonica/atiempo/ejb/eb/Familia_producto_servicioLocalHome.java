package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Familia_producto_servicio
 */
public interface Familia_producto_servicioLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Familia_producto_servicio
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Familia_producto_servicioLocalHome";
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Familia_producto_servicioLocal create(
		java.lang.Long faps_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Familia_producto_servicio
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Familia_producto_servicioLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Familia_producto_servicioKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Familia_producto_servicio
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Familia_producto_servicioLocal create(
		java.lang.Long faps_id,
		java.lang.String faps_nombre)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
