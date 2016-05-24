package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfiguracionAutomaticaSTB
 */
public interface AConfiguracionAutomaticaSTBLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfiguracionAutomaticaSTB
	 */
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfiguracionAutomaticaSTBLocalHome";	
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfiguracionAutomaticaSTBLocal create()
		throws javax.ejb.CreateException;
	
}
