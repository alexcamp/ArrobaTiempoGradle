package co.com.telefonica.atiempo.soltec.actividades.df.solucion_stb.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ASTMantenimientoRISTB
 */
public interface ASTMantenimientoRISTBLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/solucion_stb/ejb/sb/ASTMantenimientoRISTBLocalHome";
	/**
	 * Creates a default instance of Session Bean: ASTMantenimientoRISTB
	 */
	public co.com.telefonica.atiempo.soltec.actividades.df.solucion_stb.ejb.sb.ASTMantenimientoRISTBLocal create()
		throws javax.ejb.CreateException;
}
