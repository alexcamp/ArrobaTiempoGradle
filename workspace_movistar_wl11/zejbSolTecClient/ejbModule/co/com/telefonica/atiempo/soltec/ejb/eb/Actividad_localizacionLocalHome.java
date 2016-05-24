package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Actividad_localizacion
 */
public interface Actividad_localizacionLocalHome extends javax.ejb.EJBLocalHome {
	
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Actividad_localizacionLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Actividad_localizacion
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionLocal create(
		java.lang.Long act_id,
		java.lang.Integer loc_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Actividad_localizacion
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIdAct(java.lang.Long id_Act)
		throws javax.ejb.FinderException;
}
