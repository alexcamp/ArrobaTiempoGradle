package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AFinanciarEquipo
 */
public interface AFinanciarEquipoLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/ejb/sb/AFinanciarEquipoLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AFinanciarEquipo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AFinanciarEquipoLocal create()
		throws javax.ejb.CreateException;
}