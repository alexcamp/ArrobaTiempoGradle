package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarTerraSiteBuilder
 */
public interface AConfigurarTerraSiteBuilderLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarTerraSiteBuilderLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConfigurarTerraSiteBuilder
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarTerraSiteBuilderLocal create()
		throws javax.ejb.CreateException;
}