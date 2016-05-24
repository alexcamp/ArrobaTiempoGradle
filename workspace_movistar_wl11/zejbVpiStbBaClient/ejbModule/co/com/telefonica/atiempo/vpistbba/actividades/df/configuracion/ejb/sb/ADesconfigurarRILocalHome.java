package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ADesconfigurarRI
 */
public interface ADesconfigurarRILocalHome extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/ADesconfigurarRILocalHome";	

	public ADesconfigurarRILocal
	create()
		throws javax.ejb.CreateException;
}
