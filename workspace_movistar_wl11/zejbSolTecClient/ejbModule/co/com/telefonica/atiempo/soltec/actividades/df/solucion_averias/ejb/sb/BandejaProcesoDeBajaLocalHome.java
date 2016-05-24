package co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: BandejaProcesoDeBaja
 */
public interface BandejaProcesoDeBajaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: BandejaProcesoDeBaja
	 */
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/solucion_averias/ejb/sb/BandejaProcesoDeBajaLocalHome";
	
	public co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb.BandejaProcesoDeBajaLocal create()
		throws javax.ejb.CreateException;
}