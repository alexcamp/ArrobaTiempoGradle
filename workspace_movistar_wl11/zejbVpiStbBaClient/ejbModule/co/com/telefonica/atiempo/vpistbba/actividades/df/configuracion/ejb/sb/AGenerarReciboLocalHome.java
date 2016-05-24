package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AGenerarRecibo
 */
public interface AGenerarReciboLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AGenerarReciboLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AGenerarRecibo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AGenerarReciboLocal create()
		throws javax.ejb.CreateException;
}