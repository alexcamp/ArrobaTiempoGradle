package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Local Home interface for Enterprise Bean: Tipo_Estrategia
 */
public interface Tipo_EstrategiaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_Estrategia
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/inventario/ejb/Tipo_EstrategiaLocalHome";
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tipo_EstrategiaLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_Estrategia
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tipo_EstrategiaLocal findByPrimaryKey(
		java.lang.Long primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}