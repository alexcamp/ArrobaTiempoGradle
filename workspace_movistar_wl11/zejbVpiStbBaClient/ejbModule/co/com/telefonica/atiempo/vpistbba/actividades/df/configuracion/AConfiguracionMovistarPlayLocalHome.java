package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion;
/**
 * Local Home interface for Enterprise Bean: AConfiguracionMovistarPlay
 */
public interface AConfiguracionMovistarPlayLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfiguracionMovistarPlay
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/AConfiguracionMovistarPlayLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.AConfiguracionMovistarPlayLocal create()
		throws javax.ejb.CreateException;
}