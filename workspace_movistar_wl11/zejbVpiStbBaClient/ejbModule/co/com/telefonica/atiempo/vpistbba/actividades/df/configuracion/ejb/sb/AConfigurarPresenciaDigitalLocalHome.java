package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarPresenciaDigital
 */
public interface AConfigurarPresenciaDigitalLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarPresenciaDigitalLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConfigurarPresenciaDigital
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarPresenciaDigitalLocal create()
		throws javax.ejb.CreateException;
}