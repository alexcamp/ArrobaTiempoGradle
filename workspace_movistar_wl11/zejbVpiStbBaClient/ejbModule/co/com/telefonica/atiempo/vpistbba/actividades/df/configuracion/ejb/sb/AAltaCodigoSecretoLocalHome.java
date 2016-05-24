package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AAltaCodigoSecreto
 */
public interface AAltaCodigoSecretoLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AAltaCodigoSecretoLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AAltaCodigoSecreto
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltaCodigoSecretoLocal create()
		throws javax.ejb.CreateException;
}
