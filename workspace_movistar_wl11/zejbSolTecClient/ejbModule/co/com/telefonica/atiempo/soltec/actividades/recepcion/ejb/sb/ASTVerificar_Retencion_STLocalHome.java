package co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ASTVerificar_Retencion_ST
 */
public interface ASTVerificar_Retencion_STLocalHome	extends	javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/recepcion/ejb/sb/ASTVerificar_Retencion_STLocalHome";
	/**
	 * Creates a default instance of Session Bean: ASTVerificar_Retencion_ST
	 */
	public co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb.ASTVerificar_Retencion_STLocal create()
		throws javax.ejb.CreateException;
}
