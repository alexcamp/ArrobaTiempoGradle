package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Departamento
 */
public interface DepartamentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Departamento
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/DepartamentoLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal create(
		java.lang.String cod_dpt)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Departamento
	 */
	public co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
