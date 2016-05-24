package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Local Home interface for Enterprise Bean: Homologacion_ps_sva_ba
 */
public interface Homologacion_ps_sva_baLocalHome extends javax.ejb.EJBLocalHome {
	String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/inventario/ejb/Homologacion_ps_sva_baLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Homologacion_ps_sva_ba
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baLocal create(
		java.lang.Long ps_ba,
		java.lang.Long ps_sva) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Homologacion_ps_sva_ba
	 */
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baKey primaryKey)
		throws javax.ejb.FinderException;
}