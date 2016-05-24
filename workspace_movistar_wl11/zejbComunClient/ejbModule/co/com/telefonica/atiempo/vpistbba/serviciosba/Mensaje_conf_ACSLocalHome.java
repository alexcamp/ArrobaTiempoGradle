package co.com.telefonica.atiempo.vpistbba.serviciosba;
/**
 * Local Home interface for Enterprise Bean: Mensaje_conf_ACS
 */
public interface Mensaje_conf_ACSLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/serviciosba/Mensaje_conf_ACSLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_conf_ACS
	 */
	public co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal create(
		java.lang.Integer id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_conf_ACS
	 */
	public co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}