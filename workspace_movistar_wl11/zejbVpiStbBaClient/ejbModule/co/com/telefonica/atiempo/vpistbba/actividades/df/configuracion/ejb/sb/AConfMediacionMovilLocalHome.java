package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfMediacionMovil
 */
public interface AConfMediacionMovilLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfMediacionMovil
	 */
	
	public static final String JNDI_NAME= "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfMediacionMovilLocalHome";

	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfMediacionMovilLocal create()
		throws javax.ejb.CreateException;
}