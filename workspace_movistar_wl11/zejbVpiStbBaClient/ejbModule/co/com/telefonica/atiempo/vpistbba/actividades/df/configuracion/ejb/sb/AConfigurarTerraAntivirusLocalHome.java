package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarTerraAntivirus
 */
public interface AConfigurarTerraAntivirusLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarTerraAntivirusLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConfigurarTerraAntivirus
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarTerraAntivirusLocal create()
		throws javax.ejb.CreateException;
}