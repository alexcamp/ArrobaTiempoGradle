package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AMotorizado
 */
public interface AMotorizadoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AMotorizado
	 */
	
	public static final String JNDI_NAME ="ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AMotorizadoLocalHome";
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AMotorizadoLocal create()
		throws javax.ejb.CreateException;
}