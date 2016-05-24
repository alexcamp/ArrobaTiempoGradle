package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfiguracionFijaMovil
 */
public interface ARecargaFijaMovilLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ARecargaFijaMovilLocalHome";
	/**
	 * Creates a default instance of Session Bean: AConfiguracionFijaMovil
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ARecargaFijaMovilLocal create()
		throws javax.ejb.CreateException;
}