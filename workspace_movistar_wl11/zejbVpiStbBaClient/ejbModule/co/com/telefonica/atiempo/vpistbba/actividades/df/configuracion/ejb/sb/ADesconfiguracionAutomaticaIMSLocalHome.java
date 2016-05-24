package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ADesconfiguracionAutomaticaIMS
 */
public interface ADesconfiguracionAutomaticaIMSLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: ADesconfiguracionAutomaticaIMS
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ADesconfiguracionAutomaticaIMSLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ADesconfiguracionAutomaticaIMSLocal create()
		throws javax.ejb.CreateException;
}