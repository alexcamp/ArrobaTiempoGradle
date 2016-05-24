package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AObtenerInfBASigresSVA
 */
public interface AObtenerInfBASigresSVALocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: AObtenerInfBASigresSVA
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AObtenerInfBASigresSVALocalHome";
	public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AObtenerInfBASigresSVALocal create()
		throws javax.ejb.CreateException;
}
