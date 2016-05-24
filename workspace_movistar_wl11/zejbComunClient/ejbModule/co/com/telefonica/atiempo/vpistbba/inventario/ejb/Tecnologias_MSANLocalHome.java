package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Local Home interface for Enterprise Bean: Tecnologias_MSAN
 */
public interface Tecnologias_MSANLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/inventario/ejb/Tecnologias_MSANLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Tecnologias_MSAN
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANLocal create(
		java.lang.String equipo,
		java.lang.String tecnologia) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tecnologias_MSAN
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANLocal findByEquipo(java.lang.String equipo) throws javax.ejb.FinderException;
}