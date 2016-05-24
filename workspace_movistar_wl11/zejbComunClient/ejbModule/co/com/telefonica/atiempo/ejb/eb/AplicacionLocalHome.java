package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Aplicacion
 */
public interface AplicacionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Aplicacion
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/AplicacionLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal create(
		java.lang.Long ap_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Aplicacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.AplicacionKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Aplicacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal create(
		java.lang.Long ap_id,
		java.lang.String ap_nombre)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal findByNombre(java.lang.String nombre) throws javax.ejb.FinderException;
}
