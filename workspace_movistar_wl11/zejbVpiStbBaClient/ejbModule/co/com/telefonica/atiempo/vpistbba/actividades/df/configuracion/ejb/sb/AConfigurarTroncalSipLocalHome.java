package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarTroncalSip
 */
public interface AConfigurarTroncalSipLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarTroncalSipLocalHome";
	/**
	 * Creates a default instance of Session Bean: AConfigurarTroncalSip
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarTroncalSipLocal create()
		throws javax.ejb.CreateException;
}
