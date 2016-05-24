package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Local Home interface for Enterprise Bean: Catalogo_desconciliacion
 */
public interface Catalogo_desconciliacionLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Catalogo_desconciliacion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/inventario/ejb/Catalogo_desconciliacionLocalHome";
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Catalogo_desconciliacionLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Catalogo_desconciliacion
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Catalogo_desconciliacionLocal findByPrimaryKey(
		java.lang.Long primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findbyType(java.lang.Long tipo) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}