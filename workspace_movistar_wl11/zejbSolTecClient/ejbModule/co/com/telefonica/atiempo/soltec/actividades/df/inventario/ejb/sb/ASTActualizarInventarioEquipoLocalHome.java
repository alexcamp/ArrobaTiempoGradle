package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: ASTActualizarInventarioEquipo
 */
public interface ASTActualizarInventarioEquipoLocalHome
    extends
        javax.ejb.EJBLocalHome {
    public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/df/inventario/ejb/sb/ASTActualizarInventarioEquipoLocalHome";		
    /**
     * Creates a default instance of Session Bean: ASTActualizarInventarioEquipo
     */
    public co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb.ASTActualizarInventarioEquipoLocal create()
        throws javax.ejb.CreateException;
}
