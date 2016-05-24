package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Localidad
 */
public interface LocalidadLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Localidad
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/LocalidadLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal create(
		java.lang.String cod_loc)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Localidad
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.LocalidadKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Localidad
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal create(
		java.lang.String cod_loc,
		java.lang.String nombre_provincia,
		java.lang.String nombre_municipio,
		java.lang.String nombre_localidad,
		java.lang.String descripcion_localidad,
		java.lang.String ind_presuscr,
		java.lang.String ind_selecc_marcado)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal findByCod_loc(java.lang.String cod_loc) throws javax.ejb.FinderException;
}
