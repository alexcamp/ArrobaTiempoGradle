package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AEntregarKitAutoInstalacion
 */
public interface AEntregarKitAutoInstalacionLocalHome
	extends
		javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AEntregarKitAutoInstalacion
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/ejb/sb/AEntregarKitAutoInstalacionLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AEntregarKitAutoInstalacionLocal create()
		throws javax.ejb.CreateException;
}
