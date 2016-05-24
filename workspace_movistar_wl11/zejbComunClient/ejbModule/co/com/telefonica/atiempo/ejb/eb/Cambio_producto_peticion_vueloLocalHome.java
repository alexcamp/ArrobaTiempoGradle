package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Cambio_producto_peticion_vuelo
 */
public interface Cambio_producto_peticion_vueloLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Cambio_producto_peticion_vueloLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Cambio_producto_peticion_vuelo
	 */
	public co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloLocal create(
		java.lang.Double id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Cambio_producto_peticion_vuelo
	 */
	public co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findPrse_id_orig(java.lang.Double prse_id_orig) throws javax.ejb.FinderException;
}