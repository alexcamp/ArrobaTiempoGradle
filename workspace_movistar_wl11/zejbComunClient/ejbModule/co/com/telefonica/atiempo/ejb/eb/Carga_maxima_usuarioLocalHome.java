package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Carga_maxima_usuario
 */
public interface Carga_maxima_usuarioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Carga_maxima_usuario
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Carga_maxima_usuarioLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Carga_maxima_usuarioLocal create(
		java.lang.Long cmu_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Carga_maxima_usuario
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Carga_maxima_usuarioLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Carga_maxima_usuarioKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Carga_maxima_usuario
	 */
	public co.com.telefonica.atiempo.ejb.eb.Carga_maxima_usuarioLocal create(
		java.lang.Long cmu_id,
		java.lang.Long usua_id)
		throws javax.ejb.CreateException;
}
