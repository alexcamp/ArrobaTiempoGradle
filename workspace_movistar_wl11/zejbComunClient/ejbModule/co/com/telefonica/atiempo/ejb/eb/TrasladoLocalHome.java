package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Traslado
 */
public interface TrasladoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Traslado
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/TrasladoLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.TrasladoLocal create(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioLocal argProducto_servicio,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal argOperacion_comercial)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Traslado
	 */
	public co.com.telefonica.atiempo.ejb.eb.TrasladoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.TrasladoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Traslado
	 */
	public co.com.telefonica.atiempo.ejb.eb.TrasladoLocal create(
		java.lang.Long producto_servicio_ps_id,
		java.lang.Long operacion_comercial_opco_id)
		throws javax.ejb.CreateException;
}
