package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AInstalarDTHMasivo
 */
public interface AInstalarDTHMasivoLocalHome extends javax.ejb.EJBLocalHome {
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/AInstalarDTHMasivoLocalHome";
	/**
	 * Creates a default instance of Session Bean: AInstalarDTHMasivo
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.AInstalarDTHMasivoLocal create()
		throws javax.ejb.CreateException;
}
