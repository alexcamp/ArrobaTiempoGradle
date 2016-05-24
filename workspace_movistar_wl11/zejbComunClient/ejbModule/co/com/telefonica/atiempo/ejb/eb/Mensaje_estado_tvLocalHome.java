package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_estado_tv
 */
public interface Mensaje_estado_tvLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_estado_tv
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_estado_tvLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_estado_tv
	 */
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_estado_tv
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_tvLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumeroEstado(java.lang.Long peticion, java.lang.Integer estado) throws javax.ejb.FinderException;
}
