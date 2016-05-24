package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarInternet
 */
public interface AConfigurarPdVALocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarPdVALocalHome";	
	/**
	 * Creates a default instance of Session Bean: AConfigurarInternet
	 */
	public AConfigurarPdVALocal create()
		throws javax.ejb.CreateException;
}
