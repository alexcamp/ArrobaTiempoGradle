package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfirmacionPagoRecibo
 */
public interface AConfirmacionPagoReciboLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfirmacionPagoReciboLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConfirmacionPagoRecibo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfirmacionPagoReciboLocal create()
		throws javax.ejb.CreateException;
}