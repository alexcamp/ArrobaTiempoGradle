package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AInstalarTOA
 */
public interface AInstalarTOALocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AInstalarTOALocalHome";	

	/**
	 * Creates a default instance of Session Bean: AInstalarTOA
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AInstalarTOALocal create()
		throws javax.ejb.CreateException;
}