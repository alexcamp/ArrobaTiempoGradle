package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_peticion_st
 */
public interface Tipo_peticion_stLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_peticion_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Tipo_peticion_stLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_peticion_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tipo_peticion_stLocal create(
		java.lang.String id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_peticion_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tipo_peticion_stLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Tipo_peticion_stKey primaryKey)
		throws javax.ejb.FinderException;
}
