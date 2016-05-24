package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfiguracionAutomaticaMSAN
 */
public interface AConfiguracionAutomaticaMSANLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfiguracionAutomaticaMSAN
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfiguracionAutomaticaMSANLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfiguracionAutomaticaMSANLocal create()
		throws javax.ejb.CreateException;
}