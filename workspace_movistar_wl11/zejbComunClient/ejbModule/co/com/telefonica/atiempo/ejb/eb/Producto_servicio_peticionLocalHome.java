package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Producto_servicio_peticion
 */
public interface Producto_servicio_peticionLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Producto_servicio_peticionLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionLocal create(
		java.lang.Long correlativo,
		java.lang.Long fk_psp_pet_peti_numero)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Producto_servicio_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Producto_servicio_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */

	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionLocal create(
		Long correlativo,
		PeticionLocal pet,
		Operacion_comercialLocal op,
		Producto_servicioLocal ps)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Producto_servicio_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionLocal create(
		java.lang.Long correlativo,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argFk_psp_pet)
		throws javax.ejb.CreateException;
	public java.util.Collection findByIdFamiliaPs(
		java.lang.Long idFamiliaPs,
		java.lang.Long nroPeticion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumeroPsYOpCo(
		java.lang.Long petiNumero,
		java.lang.Long idPs,
		java.lang.Long opCo)
		throws javax.ejb.FinderException;
	public java.util.Collection findAllByPetiNumero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}
