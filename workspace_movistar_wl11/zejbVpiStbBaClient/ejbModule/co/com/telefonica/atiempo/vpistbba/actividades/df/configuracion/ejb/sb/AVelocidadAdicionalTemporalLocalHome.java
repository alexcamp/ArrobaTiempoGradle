package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AVelocidadAdicionalTemporal
 */
public interface AVelocidadAdicionalTemporalLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AVelocidadAdicionalTemporal
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AVelocidadAdicionalTemporalLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AVelocidadAdicionalTemporalLocal create()
		throws javax.ejb.CreateException;
}