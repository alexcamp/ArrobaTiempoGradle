package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Parametro
 */
public interface ParametroLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Parametro
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/ParametroLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.ParametroLocal create(
		java.lang.Long para_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Parametro
	 */
	public co.com.telefonica.atiempo.ejb.eb.ParametroLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.ParametroKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Parametro
	 */
	public co.com.telefonica.atiempo.ejb.eb.ParametroLocal create(
		java.lang.Long para_id,
		java.lang.String para_nombre,
		java.lang.String para_valor)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.ejb.eb.ParametroLocal findBuscaValor(java.lang.String valor) throws javax.ejb.FinderException;
}
