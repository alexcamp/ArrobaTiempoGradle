package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AControlInstalacionDTHMasivo
 */
public interface AControlInstalacionDTHMasivoLocalHome
	extends
		javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AControlInstalacionDTHMasivoLocalHome";
	/**
	 * Creates a default instance of Session Bean: AControlInstalacionDTHMasivo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AControlInstalacionDTHMasivoLocal create()
		throws javax.ejb.CreateException;
}
