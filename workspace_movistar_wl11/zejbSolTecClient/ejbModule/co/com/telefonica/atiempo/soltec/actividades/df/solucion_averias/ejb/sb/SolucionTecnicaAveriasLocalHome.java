package co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: SolucionTecnicaAverias
 */
public interface SolucionTecnicaAveriasLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: SolucionTecnicaAverias
	 */
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/solucion_averias/ejb/sb/SolucionTecnicaAveriasLocalHome";
	
	public co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb.SolucionTecnicaAveriasLocal create()
		throws javax.ejb.CreateException;
}