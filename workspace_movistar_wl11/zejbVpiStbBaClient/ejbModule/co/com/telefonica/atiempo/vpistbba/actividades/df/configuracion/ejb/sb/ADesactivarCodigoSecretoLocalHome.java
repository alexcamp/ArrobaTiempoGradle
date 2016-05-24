package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ADesactivarCodigoSecreto
 */
public interface ADesactivarCodigoSecretoLocalHome
	extends
		javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ADesactivarCodigoSecretoLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: ADesactivarCodigoSecreto
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ADesactivarCodigoSecretoLocal create()
		throws javax.ejb.CreateException;
}
