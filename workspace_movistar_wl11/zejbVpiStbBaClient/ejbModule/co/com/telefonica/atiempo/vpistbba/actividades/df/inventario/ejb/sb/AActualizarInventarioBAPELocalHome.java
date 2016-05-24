package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AActualizarInventarioBAPE
 */
public interface AActualizarInventarioBAPELocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/inventario/ejb/sb/AActualizarInventarioBAPELocalHome";
	/**
	 * Creates a default instance of Session Bean: AActualizarInventarioBAPE
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb.AActualizarInventarioBAPELocal create()
		throws javax.ejb.CreateException;
}
