package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Usuario_rol
 */
public interface Usuario_rolLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: usro_habilitado
	 */
	public java.lang.Short getUsro_habilitado();
	/**
	 * Set accessor for persistent attribute: usro_habilitado
	 */
	public void setUsro_habilitado(java.lang.Short newUsro_habilitado);
	/**
	 * Get accessor for persistent attribute: rol_supervisor
	 */
	public java.lang.String getRol_supervisor();
	/**
	 * Set accessor for persistent attribute: rol_supervisor
	 */
	public void setRol_supervisor(java.lang.String newRol_supervisor);
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal getFk_usuarol_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_usuarol_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_usuarol_rol);
	/**
	 * This method was generated for supporting the relationship role named fk_supe_2_usro.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getFk_supe_2_usro();
	/**
	 * This method was generated for supporting the relationship role named fk_supe_2_usro.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_supe_2_usro(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_supe_2_usro);
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getFk_usuarol_usua();
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_usuarol_usua(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_usuarol_usua);
}
