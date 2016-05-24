package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_estado_st
 */
public interface Mensaje_estado_stLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_estado_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Mensaje_estado_stLocalHome";
	
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Mensaje_estado_stLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_estado_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Mensaje_estado_stLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Mensaje_estado_stKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticion(java.lang.Long nroPeticion)
		throws javax.ejb.FinderException;
	public java.util.Collection findEstadoPeticion(
		java.lang.Long peticion,
		java.lang.Integer estado,
		java.lang.String codActividad)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticionSerial(
		java.lang.Long idPeticion,
		java.lang.String serial,
		java.lang.String codActividad)
		throws javax.ejb.FinderException;
}
