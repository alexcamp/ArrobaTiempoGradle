package co.com.telefonica.atiempo.soltec.actividades.df.configuracion.ejb.naked.sb;
/**
 * Local Home interface for Enterprise Bean: ACSServicioST
 */
public interface ACSServicioSTLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: ACSServicioST
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/configuracion/ejb/naked/sb/ACSServicioSTLocalHome";	
	
	public co.com.telefonica.atiempo.soltec.actividades.df.configuracion.ejb.naked.sb.ACSServicioSTLocal create()
		throws javax.ejb.CreateException;
}