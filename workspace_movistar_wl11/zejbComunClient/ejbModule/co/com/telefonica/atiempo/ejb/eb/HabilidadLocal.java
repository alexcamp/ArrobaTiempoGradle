package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Habilidad
 */
public interface HabilidadLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: habi_nombre
	 */
	public java.lang.String getHabi_nombre();
	/**
	 * Set accessor for persistent attribute: habi_nombre
	 */
	public void setHabi_nombre(java.lang.String newHabi_nombre);
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getHabilidad_usuario();
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setHabilidad_usuario(java.util.Collection aHabilidad_usuario);
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRol_habilidad();
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRol_habilidad(java.util.Collection aRol_habilidad);
}
