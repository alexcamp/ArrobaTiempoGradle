package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Grupos_Especiales
 */
public interface Grupos_EspecialesLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Grupos_Especiales
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Grupos_EspecialesLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Grupos_EspecialesLocal create(
		java.lang.Long grpe_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Grupos_Especiales
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Grupos_EspecialesLocal findByPrimaryKey(
		java.lang.Long primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findByTipo(java.lang.String tipo)
		throws javax.ejb.FinderException;
}
