package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Habilidad_usuario
 */
public interface Habilidad_usuarioLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: husu_valor
	 */
	public java.lang.String getHusu_valor();
	/**
	 * Set accessor for persistent attribute: husu_valor
	 */
	public void setHusu_valor(java.lang.String newHusu_valor);
	/**
	 * This method was generated for supporting the relationship role named fk_usua_2_husa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getFk_usua_2_husa();
	/**
	 * This method was generated for supporting the relationship role named fk_usua_2_husa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_usua_2_husa(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_usua_2_husa);
	/**
	 * This method was generated for supporting the relationship role named fk_habi_2_husu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.HabilidadLocal getFk_habi_2_husu();
	/**
	 * This method was generated for supporting the relationship role named fk_habi_2_husu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_habi_2_husu(
		co.com.telefonica.atiempo.ejb.eb.HabilidadLocal aFk_habi_2_husu);
}
