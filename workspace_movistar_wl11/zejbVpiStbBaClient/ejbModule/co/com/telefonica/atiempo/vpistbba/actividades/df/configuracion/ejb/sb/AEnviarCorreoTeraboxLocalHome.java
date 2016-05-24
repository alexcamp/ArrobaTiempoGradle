package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AEnviarCorreoTerabox
 */
public interface AEnviarCorreoTeraboxLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AEnviarCorreoTerabox
	 */
	
	public static final String JNDI_NAME= "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AEnviarCorreoTeraboxLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AEnviarCorreoTeraboxLocal create()
		throws javax.ejb.CreateException;
}