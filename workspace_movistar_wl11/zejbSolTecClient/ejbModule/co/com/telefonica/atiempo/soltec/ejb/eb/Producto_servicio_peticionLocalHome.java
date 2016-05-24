package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Producto_servicio_peticion
 */
public interface Producto_servicio_peticionLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Producto_servicio_peticionLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_peticionLocal create(
			java.lang.Long peti_numero,
			java.lang.Long correlativo,
			Operacion_comercial_stLocal oc,
			Producto_servicio_stLocal ps)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Producto_servicio_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_peticionLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Producto_servicio_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumero(java.lang.Long petiNumero)
		throws javax.ejb.FinderException;
}
