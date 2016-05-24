package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfiguracionAutomaticaIMS
 */
public interface AConfiguracionAutomaticaIMSLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AConfiguracionAutomaticaIMS
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfiguracionAutomaticaIMSLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfiguracionAutomaticaIMSLocal create()
		throws javax.ejb.CreateException;
}