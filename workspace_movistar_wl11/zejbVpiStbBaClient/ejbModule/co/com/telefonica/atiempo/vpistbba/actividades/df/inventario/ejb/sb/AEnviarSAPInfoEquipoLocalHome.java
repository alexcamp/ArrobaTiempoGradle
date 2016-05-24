package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AEnviarSAPInfoEquipo
 */
public interface AEnviarSAPInfoEquipoLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/inventario/ejb/sb/AEnviarSAPInfoEquipoLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AEnviarSAPInfoEquipo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb.AEnviarSAPInfoEquipoLocal create()
		throws javax.ejb.CreateException;
}