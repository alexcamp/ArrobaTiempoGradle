package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Producto_servicio
 */
public interface Producto_servicioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Producto_servicioLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal create(
		java.lang.Long ps_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Producto_servicio
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findTVAdicional(java.lang.Integer faps_id) throws javax.ejb.FinderException;
	public java.util.Collection findTVBasico(java.lang.Integer faps_id) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal findByPS_Nombre(java.lang.String ps_nombre) throws javax.ejb.FinderException;
}
