package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Campo_usuario
 */
public interface Campo_usuarioLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named fk_camusu_camvar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal getFk_camusu_camvar();
	/**
	 * This method was generated for supporting the relationship role named fk_camusu_camvar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_camusu_camvar(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal aFk_camusu_camvar);
	/**
	 * This method was generated for supporting the relationship role named fk_campousua_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getFk_campousua_usua();
	/**
	 * This method was generated for supporting the relationship role named fk_campousua_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_campousua_usua(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_campousua_usua);
}
