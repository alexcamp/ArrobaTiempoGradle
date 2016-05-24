package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Rol_habilidad
 */
public interface Rol_habilidadLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Rol_habilidad
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Rol_habilidadLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Rol_habilidadLocal create(
		java.lang.Long roha_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Rol_habilidad
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Rol_habilidadLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Rol_habilidad
	 */
	public co.com.telefonica.atiempo.ejb.eb.Rol_habilidadLocal create(
		java.lang.Long roha_id,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_rol_2_roha)
		throws javax.ejb.CreateException;
	public java.util.Collection findHabilidadesPorRol(java.lang.Long idRol) throws javax.ejb.FinderException;
}
