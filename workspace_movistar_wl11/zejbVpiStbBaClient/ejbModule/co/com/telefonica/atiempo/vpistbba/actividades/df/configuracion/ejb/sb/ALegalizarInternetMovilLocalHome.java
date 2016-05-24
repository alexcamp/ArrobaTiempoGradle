package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ALegalizarInternetMovil
 */
public interface ALegalizarInternetMovilLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ALegalizarInternetMovilLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: ALegalizarInternetMovil
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ALegalizarInternetMovilLocal create()
		throws javax.ejb.CreateException;
}