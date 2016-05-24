package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: RefrescarRecursosSTBBA
 */
public interface RefrescarRecursosSTBBALocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/inventario/ejb/sb/RefrescarRecursosSTBBALocalHome";
	/**
	/**
	 * Creates a default instance of Session Bean: RefrescarRecursosSTBBA
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb.RefrescarRecursosSTBBALocal create()
		throws javax.ejb.CreateException;
}