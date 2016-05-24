package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AObtenerConfiguracionEquipo
 */
public interface AObtenerConfiguracionEquipoLocalHome
    extends
        javax.ejb.EJBLocalHome {
    /**
     * Creates a default instance of Session Bean: AObtenerConfiguracionEquipo
     */
    public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/configuracion/ejb/sb/AObtenerConfiguracionEquipoLocalHome";
    public co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AObtenerConfiguracionEquipoLocal create()
        throws javax.ejb.CreateException;
}
