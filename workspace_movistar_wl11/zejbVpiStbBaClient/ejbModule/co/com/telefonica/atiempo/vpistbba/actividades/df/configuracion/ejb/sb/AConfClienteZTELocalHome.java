package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

/**
 * Local Home interface for Enterprise Bean: AConfClienteZTE
 */
public interface AConfClienteZTELocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfClienteZTELocalHome";

	/**
	 * Creates a default instance of Session Bean: AConfClienteZTE
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfClienteZTELocal create()
			throws javax.ejb.CreateException;
}