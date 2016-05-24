package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ADesconfiguracionAutomaticaSTB
 */
public interface ADesconfiguracionAutomaticaSTBLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: ADesconfiguracionAutomaticaSTB
	 */
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ADesconfiguracionAutomaticaSTBLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ADesconfiguracionAutomaticaSTBLocal create()
		throws javax.ejb.CreateException;
}
