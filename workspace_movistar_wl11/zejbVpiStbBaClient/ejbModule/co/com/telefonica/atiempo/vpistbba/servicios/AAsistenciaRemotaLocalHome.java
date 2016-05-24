package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: AAsistenciaRemota
 */
//RQ Cambio plan BA 25956 @dcardena 10/06/2014
public interface AAsistenciaRemotaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AAsistenciaRemota
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/servicios/AAsistenciaRemotaLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.servicios
	.AAsistenciaRemotaLocal create()
	throws javax.ejb.CreateException;
}