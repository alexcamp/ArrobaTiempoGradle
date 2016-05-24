package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_estado_ba
 */
public interface Mensaje_estado_baLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_estado_ba
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Mensaje_estado_baLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_estado_ba
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_baLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findEstadoPeticion(
		java.lang.Long peticion,
		java.lang.Integer estado)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_baLocal findPeticionActividadEstado(
			java.lang.Long nroPeticion,
			java.lang.Integer codEstado,
			java.lang.String codActividad)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumero(java.lang.Long peticion) throws javax.ejb.FinderException;
	public java.util.Collection findByPetiNumeroErrorActividad(java.lang.Long peticion, java.lang.String error, java.lang.String cod_actividad) throws javax.ejb.FinderException;
	public java.util.Collection findPeticionActividad(
		java.lang.Long peti,
		java.lang.String actividad) throws javax.ejb.FinderException;
}
