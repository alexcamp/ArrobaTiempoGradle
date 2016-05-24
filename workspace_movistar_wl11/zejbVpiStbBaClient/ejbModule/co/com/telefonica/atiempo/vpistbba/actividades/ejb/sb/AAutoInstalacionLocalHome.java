package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AAutoInstalacion
 */
public interface AAutoInstalacionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AAutoInstalacion
	 */
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/ejb/sb/AAutoInstalacionLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AAutoInstalacionLocal create()
		throws javax.ejb.CreateException;
}
