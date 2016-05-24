package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Causa
 */
public interface CausaLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/CausaLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Causa
	 */
	public co.com.telefonica.atiempo.ejb.eb.CausaLocal create(
		java.lang.Long caus_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Causa
	 */
	public co.com.telefonica.atiempo.ejb.eb.CausaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.CausaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Causa
	 */
	public co.com.telefonica.atiempo.ejb.eb.CausaLocal create(
		java.lang.Long caus_id,
		java.lang.String caus_codigo,
		java.lang.String caus_nombre)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.ejb.eb.CausaLocal findByCodigo(java.lang.String codigo) throws javax.ejb.FinderException;
	//public java.util.Collection findAllCausas() throws javax.ejb.FinderException;
}
