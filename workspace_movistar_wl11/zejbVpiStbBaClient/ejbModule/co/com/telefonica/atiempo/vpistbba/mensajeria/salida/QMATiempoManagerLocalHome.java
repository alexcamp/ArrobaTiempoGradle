package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;
/**
 * Local Home interface for Enterprise Bean: QMATiempoManager
 */
public interface QMATiempoManagerLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/mensajeria/salida/QMATiempoManagerLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: QMATiempoManager
	 */
	public co.com.telefonica.atiempo.vpistbba.mensajeria.salida.QMATiempoManagerLocal create()
		throws javax.ejb.CreateException;
}
