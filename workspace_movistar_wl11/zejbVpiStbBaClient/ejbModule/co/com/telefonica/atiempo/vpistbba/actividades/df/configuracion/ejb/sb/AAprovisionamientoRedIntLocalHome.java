package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AAprovisionamientoRedInt
 */
public interface AAprovisionamientoRedIntLocalHome	extends		javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AAprovisionamientoRedIntLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AAprovisionamientoRedInt
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAprovisionamientoRedIntLocal create()
		throws javax.ejb.CreateException;
}
