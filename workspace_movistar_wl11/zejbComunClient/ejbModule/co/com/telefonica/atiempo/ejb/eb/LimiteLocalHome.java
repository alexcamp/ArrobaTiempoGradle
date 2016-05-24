package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Limite
 */
public interface LimiteLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Limite
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/LimiteLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Limite
	 */
	public co.com.telefonica.atiempo.ejb.eb.LimiteLocal create(
		java.lang.Integer li_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argActividad)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Limite
	 */
	public co.com.telefonica.atiempo.ejb.eb.LimiteLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.LimiteKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Limite
	 */
	public co.com.telefonica.atiempo.ejb.eb.LimiteLocal create(
		java.lang.Integer li_tipo_semaforo,
		java.lang.Long actividad_act_id)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
