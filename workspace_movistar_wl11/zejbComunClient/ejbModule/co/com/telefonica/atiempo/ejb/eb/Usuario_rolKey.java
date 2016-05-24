package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Usuario_rol
 */
public class Usuario_rolKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_usuarol_rol_rol_id
	 */
	public java.lang.Long fk_usuarol_rol_rol_id;
	/**
	 * Implementation field for persistent attribute: fk_usuarol_usua_usua_id
	 */
	public java.lang.Long fk_usuarol_usua_usua_id;
	/**
	 * Creates an empty key for Entity Bean: Usuario_rol
	 */
	public Usuario_rolKey() {
	}
	/**
	 * Creates a key for Entity Bean: Usuario_rol
	 */
	public Usuario_rolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey argFk_usuarol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_usuarol_usua) {
		privateSetFk_usuarol_rolKey(argFk_usuarol_rol);
		privateSetFk_usuarol_usuaKey(argFk_usuarol_usua);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey) {
			co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey o =
				(co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey) otherKey;
			return (
				(this.fk_usuarol_rol_rol_id.equals(o.fk_usuarol_rol_rol_id))
					&& (this
						.fk_usuarol_usua_usua_id
						.equals(o.fk_usuarol_usua_usua_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_usuarol_rol_rol_id.hashCode()
				+ fk_usuarol_usua_usua_id.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolKey getFk_usuarol_rolKey() {
		co.com.telefonica.atiempo.ejb.eb.RolKey temp =
			new co.com.telefonica.atiempo.ejb.eb.RolKey();
		boolean fk_usuarol_rol_NULLTEST = true;
		fk_usuarol_rol_NULLTEST &= (fk_usuarol_rol_rol_id == null);
		temp.rol_id = fk_usuarol_rol_rol_id;
		if (fk_usuarol_rol_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_usuarol_rolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey inKey) {
		boolean fk_usuarol_rol_NULLTEST = (inKey == null);
		fk_usuarol_rol_rol_id = (fk_usuarol_rol_NULLTEST) ? null : inKey.rol_id;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioKey getFk_usuarol_usuaKey() {
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey temp =
			new co.com.telefonica.atiempo.ejb.eb.UsuarioKey();
		boolean fk_usuarol_usua_NULLTEST = true;
		fk_usuarol_usua_NULLTEST &= (fk_usuarol_usua_usua_id == null);
		temp.usua_id = fk_usuarol_usua_usua_id;
		if (fk_usuarol_usua_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_usuarol_usuaKey(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey inKey) {
		boolean fk_usuarol_usua_NULLTEST = (inKey == null);
		fk_usuarol_usua_usua_id =
			(fk_usuarol_usua_NULLTEST) ? null : inKey.usua_id;
	}
}
