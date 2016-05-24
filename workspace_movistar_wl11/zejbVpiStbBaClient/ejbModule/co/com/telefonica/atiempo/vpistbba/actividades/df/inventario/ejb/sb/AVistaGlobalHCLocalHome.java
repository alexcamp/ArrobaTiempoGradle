package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AVistaGlobalHC
 */
public interface AVistaGlobalHCLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/inventario/ejb/sb/AVistaGlobalHCLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AVistaGlobalHC
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb.AVistaGlobalHCLocal create()
		throws javax.ejb.CreateException;
}