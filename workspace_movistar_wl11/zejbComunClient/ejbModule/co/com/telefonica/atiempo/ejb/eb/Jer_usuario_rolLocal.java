package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Jer_usuario_rol
 */
public interface Jer_usuario_rolLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named fk_jerrol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal getFk_jerrol_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_jerrol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_jerrol_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_jerrol_rol);
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usu2.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_jerusuarol_usu2();
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usu2.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_jerusuarol_usu2(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_jerusuarol_usu2);
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_jerusuarol_usua();
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_jerusuarol_usua(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_jerusuarol_usua);
}
