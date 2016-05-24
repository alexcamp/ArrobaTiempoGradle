package co.com.telefonica.atiempo.soltec.actividades.factory.df.inventario;
/**
 * Local Home interface for Enterprise Bean: ASTConfPresenciaDigitalST
 */
public interface ASTConfPresenciaDigitalSTLocalHome
	extends
		javax.ejb.EJBLocalHome {
	String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/actividades/factory/df/inventario/ASTConfPresenciaDigitalSTLocalHome";

	/**
	 * Creates a default instance of Session Bean: ASTConfPresenciaDigitalST
	 */
	public co.com.telefonica.atiempo.soltec.actividades.factory.df.inventario.ASTConfPresenciaDigitalSTLocal create()
		throws javax.ejb.CreateException;
}