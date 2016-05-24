package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AAsistenciaClienteRemota
 */
public interface AAsistenciaClienteRemotaLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AAsistenciaClienteRemota
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/ejb/sb/AAsistenciaClienteRemotaLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AAsistenciaClienteRemotaLocal create()
		throws javax.ejb.CreateException;
}
