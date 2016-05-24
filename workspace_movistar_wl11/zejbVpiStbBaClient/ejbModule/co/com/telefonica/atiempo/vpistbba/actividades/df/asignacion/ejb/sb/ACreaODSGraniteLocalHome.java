package co.com.telefonica.atiempo.vpistbba.actividades.df.asignacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ACreaODSGranite
 */
public interface ACreaODSGraniteLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: ACreaODSGranite
	 */
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/asignacion/ejb/sb/ACreaODSGraniteLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.asignacion.ejb.sb.ACreaODSGraniteLocal create()
		throws javax.ejb.CreateException;
}
