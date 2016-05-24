package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Tmp_Notificacion_SAP
 */
public interface Tmp_Notificacion_SAPLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tmp_Notificacion_SAP
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Tmp_Notificacion_SAPLocalHome";
	public co.com.telefonica.atiempo.vpistbba.servicios.Tmp_Notificacion_SAPLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tmp_Notificacion_SAP
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tmp_Notificacion_SAPLocal findByPrimaryKey(
		java.lang.Long primaryKey) throws javax.ejb.FinderException;
}