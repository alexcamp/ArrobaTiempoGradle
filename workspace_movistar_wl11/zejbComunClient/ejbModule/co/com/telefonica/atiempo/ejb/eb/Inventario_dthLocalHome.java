package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Inventario_dth
 */
public interface Inventario_dthLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Inventario_dth
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Inventario_dthLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Inventario_dthLocal create(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Inventario_dth
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Inventario_dthLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Inventario_dthKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Inventario_dthLocal findByPeticion(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
}
