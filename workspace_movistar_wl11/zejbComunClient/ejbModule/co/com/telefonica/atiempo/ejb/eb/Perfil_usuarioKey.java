package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Perfil_usuario
 */
public class Perfil_usuarioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_perfusu_perf_perf_id
	 */
	public java.lang.Long fk_perfusu_perf_perf_id;
	/**
	 * Implementation field for persistent attribute: fk_perfusu_usu_usua_id
	 */
	public java.lang.Long fk_perfusu_usu_usua_id;
	/**
	 * Creates an empty key for Entity Bean: Perfil_usuario
	 */
	public Perfil_usuarioKey() {
	}
	/**
	 * Creates a key for Entity Bean: Perfil_usuario
	 */
	public Perfil_usuarioKey(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_perfusu_usu,
		co.com.telefonica.atiempo.ejb.eb.PerfilKey argFk_perfusu_perf) {
		privateSetFk_perfusu_usuKey(argFk_perfusu_usu);
		privateSetFk_perfusu_perfKey(argFk_perfusu_perf);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioKey) {
			co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioKey o =
				(co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioKey) otherKey;
			return (
				(this.fk_perfusu_usu_usua_id.equals(o.fk_perfusu_usu_usua_id))
					&& (this
						.fk_perfusu_perf_perf_id
						.equals(o.fk_perfusu_perf_perf_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_perfusu_usu_usua_id.hashCode()
				+ fk_perfusu_perf_perf_id.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PerfilKey getFk_perfusu_perfKey() {
		co.com.telefonica.atiempo.ejb.eb.PerfilKey temp =
			new co.com.telefonica.atiempo.ejb.eb.PerfilKey();
		boolean fk_perfusu_perf_NULLTEST = true;
		fk_perfusu_perf_NULLTEST &= (fk_perfusu_perf_perf_id == null);
		temp.perf_id = fk_perfusu_perf_perf_id;
		if (fk_perfusu_perf_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_perfusu_perfKey(
		co.com.telefonica.atiempo.ejb.eb.PerfilKey inKey) {
		boolean fk_perfusu_perf_NULLTEST = (inKey == null);
		fk_perfusu_perf_perf_id =
			(fk_perfusu_perf_NULLTEST) ? null : inKey.perf_id;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_usu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioKey getFk_perfusu_usuKey() {
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey temp =
			new co.com.telefonica.atiempo.ejb.eb.UsuarioKey();
		boolean fk_perfusu_usu_NULLTEST = true;
		fk_perfusu_usu_NULLTEST &= (fk_perfusu_usu_usua_id == null);
		temp.usua_id = fk_perfusu_usu_usua_id;
		if (fk_perfusu_usu_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_usu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_perfusu_usuKey(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey inKey) {
		boolean fk_perfusu_usu_NULLTEST = (inKey == null);
		fk_perfusu_usu_usua_id =
			(fk_perfusu_usu_NULLTEST) ? null : inKey.usua_id;
	}
}
