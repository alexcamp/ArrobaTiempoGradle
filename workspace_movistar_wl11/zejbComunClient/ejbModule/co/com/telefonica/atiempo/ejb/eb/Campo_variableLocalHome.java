package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Campo_variable
 */
public interface Campo_variableLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Campo_variable
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Campo_variableLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal create(
		java.lang.Short cv_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Campo_variable
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Campo_variable
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal create(
		java.lang.Short cv_id,
		java.lang.String cv_nombre_int,
		java.lang.String cv_nombre_ext)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal create(
		String cv_nombre_int,
		String cv_nombre_ext)
		throws javax.ejb.CreateException;
}
