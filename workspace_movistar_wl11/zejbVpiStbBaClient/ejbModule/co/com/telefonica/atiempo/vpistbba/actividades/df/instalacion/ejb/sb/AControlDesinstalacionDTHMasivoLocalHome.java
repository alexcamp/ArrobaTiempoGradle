package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AControlDesinstalacionDTHMasivo
 */
public interface AControlDesinstalacionDTHMasivoLocalHome
	extends
		javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AControlDesinstalacionDTHMasivoLocalHome";
	/**
	 * Creates a default instance of Session Bean: AControlDesinstalacionDTHMasivo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AControlDesinstalacionDTHMasivoLocal create()
		throws javax.ejb.CreateException;
}
