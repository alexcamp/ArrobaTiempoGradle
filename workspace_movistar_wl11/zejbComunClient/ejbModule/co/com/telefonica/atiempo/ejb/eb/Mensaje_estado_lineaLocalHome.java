package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_estado_linea
 */
public interface Mensaje_estado_lineaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_estado_linea
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_estado_lineaLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_estado_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_lineaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Mensaje_estado_lineaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findEstadoPeticion(
		java.lang.Long peticion,
		java.lang.Integer estado)
		throws javax.ejb.FinderException;
	public java.util.Collection findUltimaAcccion(
		java.lang.Long peticion,
		java.lang.String accion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticionEstadoActividad(java.lang.Long nroPeticion, java.lang.Integer estado, java.lang.String actividad) throws javax.ejb.FinderException;
	public java.util.Collection findByPetiActi(
		java.lang.Long petinumero,
		java.lang.String Actividad) throws javax.ejb.FinderException;
}
