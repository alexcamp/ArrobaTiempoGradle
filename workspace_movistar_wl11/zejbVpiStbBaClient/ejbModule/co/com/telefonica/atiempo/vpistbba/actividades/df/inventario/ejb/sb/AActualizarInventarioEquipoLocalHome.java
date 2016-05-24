package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: AActualizarInventarioEquipo
 */
public interface AActualizarInventarioEquipoLocalHome
    extends
        javax.ejb.EJBLocalHome {
    
    public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/inventario/ejb/sb/AActualizarInventarioEquipoLocalHome";
    /**
     * Creates a default instance of Session Bean: AActualizarInventarioEquipo
     */
    public co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb.AActualizarInventarioEquipoLocal create()
        throws javax.ejb.CreateException;
}
