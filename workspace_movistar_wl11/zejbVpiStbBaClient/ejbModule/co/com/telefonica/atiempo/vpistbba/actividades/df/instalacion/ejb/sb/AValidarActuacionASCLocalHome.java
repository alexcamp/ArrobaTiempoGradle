package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AValidarActuacionASC
 */
public interface AValidarActuacionASCLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AValidarActuacionASCLocalHome";

	/**
	 * Creates a default instance of Session Bean: AValidarActuacionASC
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AValidarActuacionASCLocal create()
		throws javax.ejb.CreateException;
}