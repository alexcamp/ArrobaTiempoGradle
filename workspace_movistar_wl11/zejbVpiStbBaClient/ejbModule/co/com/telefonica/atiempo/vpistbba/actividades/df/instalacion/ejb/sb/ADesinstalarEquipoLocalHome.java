package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

/**
 * @author idrincon
 * req 1060
 * Local Home interface for ADesinstalarEquipo
 */
public interface ADesinstalarEquipoLocalHome extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/ADesinstalarEquipoLocalHome";

	/**
	 * Default create method.
	 */
	public co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.ADesinstalarEquipoLocal create()
			throws javax.ejb.CreateException;

}