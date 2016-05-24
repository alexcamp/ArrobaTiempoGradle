package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Codigo_st
 */
public interface Codigo_stLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Codigo_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Codigo_stLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Codigo_st
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Codigo_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_stLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stKey primaryKey)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_stLocal findByTipoAndAtributo(
			java.lang.Integer tipo,
			java.lang.String codigo)
		throws javax.ejb.FinderException;
	public java.util.Collection findByCodigo(java.lang.String codigo)
		throws javax.ejb.FinderException;
}
