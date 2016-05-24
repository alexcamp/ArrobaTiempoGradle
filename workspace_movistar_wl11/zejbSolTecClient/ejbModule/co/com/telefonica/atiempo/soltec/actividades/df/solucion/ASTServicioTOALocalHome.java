package co.com.telefonica.atiempo.soltec.actividades.df.solucion;
/**
 * Local Home interface for Enterprise Bean: ASTServicioTOA
 */
public interface ASTServicioTOALocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: ASTServicioTOA
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/solucion/ASTServicioTOALocalHome";	

	public co.com.telefonica.atiempo.soltec.actividades.df.solucion.ASTServicioTOALocal create()
		throws javax.ejb.CreateException;
}