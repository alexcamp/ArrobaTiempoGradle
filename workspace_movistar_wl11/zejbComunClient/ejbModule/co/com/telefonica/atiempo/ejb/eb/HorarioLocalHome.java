package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Horario
 */
public interface HorarioLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Horario
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/HorarioLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Horario
	 */
	public co.com.telefonica.atiempo.ejb.eb.HorarioLocal create(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argAplicacion,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argRol)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Horario
	 */
	public co.com.telefonica.atiempo.ejb.eb.HorarioLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.HorarioKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Horario
	 */
	public co.com.telefonica.atiempo.ejb.eb.HorarioLocal create(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		java.lang.Long aplicacion_ap_id,
		java.lang.Long rol_rol_id)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
