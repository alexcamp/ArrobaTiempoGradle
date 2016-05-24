package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AControlInstalacionTOA
 */
public interface AControlInstalacionTOALocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AControlInstalacionTOALocalHome";	

	
	/**
	 * Creates a default instance of Session Bean: AControlInstalacionTOA
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AControlInstalacionTOALocal create()
		throws javax.ejb.CreateException;
}