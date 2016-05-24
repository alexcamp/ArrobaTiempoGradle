package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Perfil_usuario
 */
public interface Perfil_usuarioLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PerfilLocal getFk_perfusu_perf();
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_perfusu_perf(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal aFk_perfusu_perf);
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_usu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getFk_perfusu_usu();
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_usu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_perfusu_usu(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_perfusu_usu);
}
