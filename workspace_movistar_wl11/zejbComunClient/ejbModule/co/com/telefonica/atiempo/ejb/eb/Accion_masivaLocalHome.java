package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Accion_masiva
 */
public interface Accion_masivaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Accion_masiva
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Accion_masivaLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Accion_masivaLocal create(
		java.lang.Long acma_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Accion_masiva
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Accion_masivaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Accion_masivaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByRol(java.lang.Long idRol) throws javax.ejb.FinderException;
}
