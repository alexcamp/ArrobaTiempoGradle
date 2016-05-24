package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfiguracionInternetMovil
 */
public interface AConfiguracionInternetMovilLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfiguracionInternetMovilLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConfiguracionInternetMovil
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfiguracionInternetMovilLocal create()
		throws javax.ejb.CreateException;
}