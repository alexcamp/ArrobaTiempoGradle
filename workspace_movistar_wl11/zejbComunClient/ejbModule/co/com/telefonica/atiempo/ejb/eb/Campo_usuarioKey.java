package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Campo_usuario
 */
public class Campo_usuarioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_camusu_camvar_cv_id
	 */
	public java.lang.Short fk_camusu_camvar_cv_id;
	/**
	 * Implementation field for persistent attribute: fk_campousua_usua_usua_id
	 */
	public java.lang.Long fk_campousua_usua_usua_id;
	/**
	 * Creates an empty key for Entity Bean: Campo_usuario
	 */
	public Campo_usuarioKey() {
	}
	/**
	 * Creates a key for Entity Bean: Campo_usuario
	 */
	public Campo_usuarioKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey argFk_camusu_camvar,
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_campousua_usua) {
		privateSetFk_camusu_camvarKey(argFk_camusu_camvar);
		privateSetFk_campousua_usuaKey(argFk_campousua_usua);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Campo_usuarioKey) {
			co.com.telefonica.atiempo.ejb.eb.Campo_usuarioKey o =
				(co.com.telefonica.atiempo.ejb.eb.Campo_usuarioKey) otherKey;
			return (
				(this.fk_camusu_camvar_cv_id.equals(o.fk_camusu_camvar_cv_id))
					&& (this
						.fk_campousua_usua_usua_id
						.equals(o.fk_campousua_usua_usua_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_camusu_camvar_cv_id.hashCode()
				+ fk_campousua_usua_usua_id.hashCode());
	}
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
		.Campo_variableKey getFk_camusu_camvarKey() {
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Campo_variableKey();
		boolean fk_camusu_camvar_NULLTEST = true;
		fk_camusu_camvar_NULLTEST &= (fk_camusu_camvar_cv_id == null);
		temp.cv_id = fk_camusu_camvar_cv_id;
		if (fk_camusu_camvar_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_camusu_camvar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_camusu_camvarKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey inKey) {
		boolean fk_camusu_camvar_NULLTEST = (inKey == null);
		fk_camusu_camvar_cv_id =
			(fk_camusu_camvar_NULLTEST) ? null : inKey.cv_id;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_campousua_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioKey getFk_campousua_usuaKey() {
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey temp =
			new co.com.telefonica.atiempo.ejb.eb.UsuarioKey();
		boolean fk_campousua_usua_NULLTEST = true;
		fk_campousua_usua_NULLTEST &= (fk_campousua_usua_usua_id == null);
		temp.usua_id = fk_campousua_usua_usua_id;
		if (fk_campousua_usua_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_campousua_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_campousua_usuaKey(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey inKey) {
		boolean fk_campousua_usua_NULLTEST = (inKey == null);
		fk_campousua_usua_usua_id =
			(fk_campousua_usua_NULLTEST) ? null : inKey.usua_id;
	}
}
