package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ASTVistaGlobal
 */
public interface ASTVistaGlobalLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/inventario/ejb/sb/ASTVistaGlobalLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: ASTVistaGlobal
	 */
	public co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb.ASTVistaGlobalLocal create()
		throws javax.ejb.CreateException;
}