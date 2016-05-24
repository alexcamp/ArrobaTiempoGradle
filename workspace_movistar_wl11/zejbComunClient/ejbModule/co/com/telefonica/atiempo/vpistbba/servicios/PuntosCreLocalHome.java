package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: PuntosCre
 */
public interface PuntosCreLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/PuntosCreLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: PuntosCre
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: PuntosCre
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreClave primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocal findbyPk(java.lang.String tipo_canal, java.lang.String zona, java.lang.String ciudad) throws javax.ejb.FinderException;
}