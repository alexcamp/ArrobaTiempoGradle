package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfPaqueteMovil
 */
public interface AConfPaqueteMovilLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfPaqueteMovil
	 */
	public static final String JNDI_NAME= "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfPaqueteMovilLocalHome";

	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfPaqueteMovilLocal create()
		throws javax.ejb.CreateException;
}