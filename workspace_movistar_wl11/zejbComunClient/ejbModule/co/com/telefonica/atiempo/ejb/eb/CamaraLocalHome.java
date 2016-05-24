package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Camara
 */
public interface CamaraLocalHome extends javax.ejb.EJBLocalHome {
	public 	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/CamaraLocalHome";
											
	/**
	 * Creates an instance from a key for Entity Bean: Camara
	 */
	public co.com.telefonica.atiempo.ejb.eb.CamaraLocal create(
		java.lang.Long petiNumero,
		java.lang.String cameraSerial) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Camara
	 */
	public co.com.telefonica.atiempo.ejb.eb.CamaraLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.CamaraKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticion(java.lang.Long numeroPeticion) throws javax.ejb.FinderException;
}