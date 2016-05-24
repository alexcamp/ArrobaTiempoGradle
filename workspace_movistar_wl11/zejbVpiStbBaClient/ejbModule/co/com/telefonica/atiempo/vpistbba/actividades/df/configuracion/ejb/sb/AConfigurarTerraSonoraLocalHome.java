package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AConfigurarTerraSonora
 */
public interface AConfigurarTerraSonoraLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AConfigurarTerraSonoraLocalHome";
	
	/**
	 * Creates a default instance of Session Bean: AConfigurarTerraSonora
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarTerraSonoraLocal create()
		throws javax.ejb.CreateException;
}