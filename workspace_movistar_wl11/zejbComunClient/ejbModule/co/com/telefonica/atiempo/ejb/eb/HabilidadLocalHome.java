package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Habilidad
 */
public interface HabilidadLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Habilidad
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/HabilidadLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.HabilidadLocal create(
		java.lang.Long habi_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Habilidad
	 */
	public co.com.telefonica.atiempo.ejb.eb.HabilidadLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.HabilidadKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.HabilidadLocal findByNombre(java.lang.String nombre) throws javax.ejb.FinderException;
}
