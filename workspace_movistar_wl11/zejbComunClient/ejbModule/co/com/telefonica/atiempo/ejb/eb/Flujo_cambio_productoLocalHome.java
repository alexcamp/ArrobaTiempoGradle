package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Flujo_cambio_producto
 */
public interface Flujo_cambio_productoLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_cambio_producto
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Flujo_cambio_productoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_cambio_producto
	 */
	public co.com.telefonica.atiempo.ejb.eb.Flujo_cambio_productoLocal create(
		java.lang.Long id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Flujo_cambio_producto
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Flujo_cambio_productoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Flujo_cambio_productoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection finderByPsOrigenPsDestino(java.lang.Long idPsOrig, java.lang.Long idPsDest) throws javax.ejb.FinderException;
}
