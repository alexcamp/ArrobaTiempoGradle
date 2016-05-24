package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: ZonasCoberturaPSMarca
 */
public interface ZonasCoberturaPSMarcaLocalHome extends javax.ejb.EJBLocalHome {
	String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/ZonasCoberturaPSMarcaLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: ZonasCoberturaPSMarca
	 */
	public co.com.telefonica.atiempo.ejb.eb.ZonasCoberturaPSMarcaLocal create(
		java.lang.Integer id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: ZonasCoberturaPSMarca
	 */
	public co.com.telefonica.atiempo.ejb.eb.ZonasCoberturaPSMarcaLocal findByPrimaryKey(
		java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.ZonasCoberturaPSMarcaLocal findByZonaAnterior(java.lang.String zonaAnterior, long localidad) throws javax.ejb.FinderException;
	public java.util.Collection findByLocalidad(java.lang.String localidad)
		throws javax.ejb.FinderException;
}