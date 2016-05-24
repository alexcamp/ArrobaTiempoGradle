package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ABajaAbonadoRI
 */
public interface ABajaAbonadoRILocalHome extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ABajaAbonadoRILocalHome";

	/**
	 * Creates a default instance of Session Bean: ABajaAbonadoRI
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ABajaAbonadoRILocal create()
		throws javax.ejb.CreateException;
}
