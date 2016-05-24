package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_codigo
 */
public interface Tipo_codigoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_codigo
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Tipo_codigoLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_codigo
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoLocal create(
		java.lang.Integer tipo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_codigo
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tipo_codigoLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoKey primaryKey)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tipo_codigoLocal findByAtributo(
		java.lang.String atributo)
		throws javax.ejb.FinderException;
}
