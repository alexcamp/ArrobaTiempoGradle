package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Perfil
 */
public interface PerfilLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: perf_descripcion
	 */
	public java.lang.String getPerf_descripcion();
	/**
	 * Set accessor for persistent attribute: perf_descripcion
	 */
	public void setPerf_descripcion(java.lang.String newPerf_descripcion);
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPerfil_usuario();
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPerfil_usuario(java.util.Collection aPerfil_usuario);
	/**
	 * This method was generated for supporting the relationship role named menu_perfil.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMenu_perfil();
	/**
	 * This method was generated for supporting the relationship role named menu_perfil.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMenu_perfil(java.util.Collection aMenu_perfil);
}
