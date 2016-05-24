package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Tipo_prod_tutor_web
 */
public interface Tipo_prod_tutor_webLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Tipo_prod_tutor_webLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_prod_tutor_web
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webLocal create(
		java.lang.String ps_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_prod_tutor_web
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webKey primaryKey)
		throws javax.ejb.FinderException;
}