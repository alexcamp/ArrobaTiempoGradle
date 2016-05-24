package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ASTNotificacionSAP
 */
public interface ASTNotificacionSAPLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/inventario/ejb/sb/ASTNotificacionSAPLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: ASTNotificacionSAP
	 */
	public co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb.ASTNotificacionSAPLocal create()
		throws javax.ejb.CreateException;
}