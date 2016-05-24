package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tecnico
 */
public interface TecnicoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tecnico
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/TecnicoLocalHome";
	
	
	/**
	 * Creates an instance from a key for Entity Bean: Tecnico
	 */
	public co.com.telefonica.atiempo.ejb.eb.TecnicoLocal create(
		java.lang.String cod_tecnico)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tecnico
	 */
	public co.com.telefonica.atiempo.ejb.eb.TecnicoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.TecnicoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByEmpresa(java.lang.Long empresaId)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findAllHab() throws javax.ejb.FinderException;
}
