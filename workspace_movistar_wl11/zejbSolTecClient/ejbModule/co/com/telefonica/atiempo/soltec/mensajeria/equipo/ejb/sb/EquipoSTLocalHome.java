package co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: EquipoST
 */
public interface EquipoSTLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * Creates a default instance of Session Bean: EquipoST
     */
    public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/mensajeria/equipo/ejb/sb/EquipoSTLocalHome";
    public co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal create()
        throws javax.ejb.CreateException;
}
