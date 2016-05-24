package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: CodigoSt
 */
public interface CodigoStLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/cancelacion/ejb/sb/CodigoStLocalHome";
	/**
	 * Creates a default instance of Session Bean: CodigoSt
	 */
	public co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.CodigoStLocal create()throws javax.ejb.CreateException;
} 