package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Ciclos_facturacion
 */
public interface Ciclos_facturacionLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Ciclos_facturacionLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Ciclos_facturacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal create(
		java.lang.Integer ciclo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Ciclos_facturacion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal findDayInClycle(
		java.lang.Integer dia_inicial,
		java.lang.Integer dia_final) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal findByPrimary(
		int ciclo) throws javax.ejb.FinderException;
}