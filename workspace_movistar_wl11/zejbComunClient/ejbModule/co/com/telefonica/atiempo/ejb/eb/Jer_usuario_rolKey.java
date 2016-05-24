package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Jer_usuario_rol
 */
public class Jer_usuario_rolKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_jerrol_rol_rol_id
	 */
	public java.lang.Long fk_jerrol_rol_rol_id;
	/**
	 * Implementation field for persistent attribute: fk_jerusuarol_usu2_usua_id
	 */
	public java.lang.Long fk_jerusuarol_usu2_usua_id;
	/**
	 * Implementation field for persistent attribute: fk_jerusuarol_usua_usua_id
	 */
	public java.lang.Long fk_jerusuarol_usua_usua_id;
	/**
	 * Creates an empty key for Entity Bean: Jer_usuario_rol
	 */
	public Jer_usuario_rolKey() {
	}
	/**
	 * Creates a key for Entity Bean: Jer_usuario_rol
	 */
	public Jer_usuario_rolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey argFk_jerrol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_jerusuarol_usu2,
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_jerusuarol_usua) {
		privateSetFk_jerrol_rolKey(argFk_jerrol_rol);
		privateSetFk_jerusuarol_usu2Key(argFk_jerusuarol_usu2);
		privateSetFk_jerusuarol_usuaKey(argFk_jerusuarol_usua);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey) {
			co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey o =
				(co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey) otherKey;
			return (
				(this.fk_jerrol_rol_rol_id.equals(o.fk_jerrol_rol_rol_id))
					&& (this
						.fk_jerusuarol_usu2_usua_id
						.equals(o.fk_jerusuarol_usu2_usua_id))
					&& (this
						.fk_jerusuarol_usua_usua_id
						.equals(o.fk_jerusuarol_usua_usua_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_jerrol_rol_rol_id.hashCode()
				+ fk_jerusuarol_usu2_usua_id.hashCode()
				+ fk_jerusuarol_usua_usua_id.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_jerrol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolKey getFk_jerrol_rolKey() {
		co.com.telefonica.atiempo.ejb.eb.RolKey temp =
			new co.com.telefonica.atiempo.ejb.eb.RolKey();
		boolean fk_jerrol_rol_NULLTEST = true;
		fk_jerrol_rol_NULLTEST &= (fk_jerrol_rol_rol_id == null);
		temp.rol_id = fk_jerrol_rol_rol_id;
		if (fk_jerrol_rol_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_jerrol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_jerrol_rolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey inKey) {
		boolean fk_jerrol_rol_NULLTEST = (inKey == null);
		fk_jerrol_rol_rol_id = (fk_jerrol_rol_NULLTEST) ? null : inKey.rol_id;
	}
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
		.UsuarioKey getFk_jerusuarol_usu2Key() {
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey temp =
			new co.com.telefonica.atiempo.ejb.eb.UsuarioKey();
		boolean fk_jerusuarol_usu2_NULLTEST = true;
		fk_jerusuarol_usu2_NULLTEST &= (fk_jerusuarol_usu2_usua_id == null);
		temp.usua_id = fk_jerusuarol_usu2_usua_id;
		if (fk_jerusuarol_usu2_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usu2.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_jerusuarol_usu2Key(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey inKey) {
		boolean fk_jerusuarol_usu2_NULLTEST = (inKey == null);
		fk_jerusuarol_usu2_usua_id =
			(fk_jerusuarol_usu2_NULLTEST) ? null : inKey.usua_id;
	}
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
		.UsuarioKey getFk_jerusuarol_usuaKey() {
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey temp =
			new co.com.telefonica.atiempo.ejb.eb.UsuarioKey();
		boolean fk_jerusuarol_usua_NULLTEST = true;
		fk_jerusuarol_usua_NULLTEST &= (fk_jerusuarol_usua_usua_id == null);
		temp.usua_id = fk_jerusuarol_usua_usua_id;
		if (fk_jerusuarol_usua_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_jerusuarol_usuaKey(
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey inKey) {
		boolean fk_jerusuarol_usua_NULLTEST = (inKey == null);
		fk_jerusuarol_usua_usua_id =
			(fk_jerusuarol_usua_NULLTEST) ? null : inKey.usua_id;
	}
}
