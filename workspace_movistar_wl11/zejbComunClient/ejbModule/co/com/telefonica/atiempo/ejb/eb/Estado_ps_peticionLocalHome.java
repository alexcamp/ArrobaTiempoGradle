package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Estado_ps_peticion
 */
public interface Estado_ps_peticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Estado_ps_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Estado_ps_peticionLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Estado_ps_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Estado_ps_peticionLocal findByPrimaryKey(
			co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal create(
		Long correlativo,
		Producto_servicioLocal producto_servicioLocal,
		Producto_servicio_peticionLocal producto_servicio_peticionLocal)
		throws javax.ejb.CreateException;
}
