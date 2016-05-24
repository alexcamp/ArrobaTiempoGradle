package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Empresa
 */
public interface EmpresaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Empresa
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/EmpresaLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Empresa
	 */
	public co.com.telefonica.atiempo.ejb.eb.EmpresaLocal create(
		java.lang.Long empr_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Empresa
	 */
	public co.com.telefonica.atiempo.ejb.eb.EmpresaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.EmpresaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
