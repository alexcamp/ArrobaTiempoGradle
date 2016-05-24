package co.com.telefonica.atiempo.vpistbba.instalacion.ejb;
/**
 * Local Home interface for Enterprise Bean: TOAServicio
 */
public interface TOAServicioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: TOAServicio
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/instalacion/ejb/TOAServicioLocalHome";
	public co.com.telefonica.atiempo.vpistbba.instalacion.ejb.TOAServicioLocal create()
		throws javax.ejb.CreateException;
}