package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Producto_servicio_en_vuelo
 */
public interface Producto_servicio_en_vueloLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Producto_servicio_en_vueloLocalHome"; 
	
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_en_vuelo
	 */
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal create(
		java.lang.Long peti_numero,
		java.lang.Long correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Producto_servicio_en_vuelo
	 */
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiTipoPeticion(java.lang.Long peti_numero, java.lang.String tipo_peticion) throws javax.ejb.FinderException;
	public java.util.Collection findByPeti_Numero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}