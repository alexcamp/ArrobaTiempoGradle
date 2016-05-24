package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Tutor_web
 */
public interface Tutor_webLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tutor_web
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Tutor_webLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Tutor_web
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webLocal create(
		java.lang.Long peti_numero,
		java.lang.Integer correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tutor_web
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webLocal findMaxCorrelation() throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Tutor_web
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webLocal create(
		java.lang.Integer correlativo,
		java.lang.Long peti_numero) throws javax.ejb.CreateException;
}