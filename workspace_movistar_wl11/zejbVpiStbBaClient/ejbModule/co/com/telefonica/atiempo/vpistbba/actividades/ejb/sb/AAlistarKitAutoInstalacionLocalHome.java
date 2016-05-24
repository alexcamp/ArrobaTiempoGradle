package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AAlistarKitAutoInstalacion
 */
public interface AAlistarKitAutoInstalacionLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AAlistarKitAutoInstalacion
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/ejb/sb/AAlistarKitAutoInstalacionLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AAlistarKitAutoInstalacionLocal create()
		throws javax.ejb.CreateException;
}
