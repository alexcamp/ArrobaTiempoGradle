package co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AVerificar_Retencion
 */
public interface AVerificar_RetencionLocalHome extends javax.ejb.EJBLocalHome {
	
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/recepcion/ejb/sb/AVerificar_RetencionLocalHome";
	/**
	 * Creates a default instance of Session Bean: AVerificar_Retencion
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb.AVerificar_RetencionLocal create()
		throws javax.ejb.CreateException;
}
