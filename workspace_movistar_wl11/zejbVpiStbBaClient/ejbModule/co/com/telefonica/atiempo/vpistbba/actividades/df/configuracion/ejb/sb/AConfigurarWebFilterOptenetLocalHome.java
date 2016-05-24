package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarWebFilterOptenet
 */
public interface AConfigurarWebFilterOptenetLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfigurarWebFilterOptenet
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarWebFilterOptenetLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarWebFilterOptenetLocal create()
		throws javax.ejb.CreateException;
}
