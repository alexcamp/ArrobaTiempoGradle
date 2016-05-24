package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AAsistenciaCliente
 */
public interface AAsistenciaClienteLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AAsistenciaCliente
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/ejb/sb/AAsistenciaClienteLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AAsistenciaClienteLocal create()
		throws javax.ejb.CreateException;
}
