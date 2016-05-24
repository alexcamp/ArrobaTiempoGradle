package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Operacion_comercial
 */
public interface Operacion_comercialLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Operacion_comercial
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Operacion_comercialLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal create(
		java.lang.Long opco_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Operacion_comercial
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialLocal findByPrimaryKey(
			co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Operacion_comercial
	 */
	public co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal create(
		java.lang.Long opco_id,
		java.lang.Integer titr_id)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal findTipoA() throws javax.ejb.FinderException;
}
