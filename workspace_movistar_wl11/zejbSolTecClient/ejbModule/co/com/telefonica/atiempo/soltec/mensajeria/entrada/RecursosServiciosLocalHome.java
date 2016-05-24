package co.com.telefonica.atiempo.soltec.mensajeria.entrada;
/**
 * Local Home interface for Enterprise Bean: RecursosServicios
 */
public interface RecursosServiciosLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/mensajeria/entrada/RecursosServiciosLocalHome";	

	/**
	 * Creates a default instance of Session Bean: RecursosServicios
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.mensajeria
		.entrada
		.RecursosServiciosLocal create()
		throws javax.ejb.CreateException;
}
