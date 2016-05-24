package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.naked.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarACS
 */
public interface AConfigurarACSLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfigurarACS
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/naked/sb/AConfigurarACSLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.naked.sb.AConfigurarACSLocal create()
		throws javax.ejb.CreateException;
}