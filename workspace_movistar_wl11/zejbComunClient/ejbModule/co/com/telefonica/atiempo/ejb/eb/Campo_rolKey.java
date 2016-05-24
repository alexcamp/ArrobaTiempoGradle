package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Campo_rol
 */
public class Campo_rolKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_camrol_2_cam_cv_id
	 */
	public java.lang.Short fk_camrol_2_cam_cv_id;
	/**
	 * Implementation field for persistent attribute: fk_camrol_2_rol_rol_id
	 */
	public java.lang.Long fk_camrol_2_rol_rol_id;
	/**
	 * Creates an empty key for Entity Bean: Campo_rol
	 */
	public Campo_rolKey() {
	}
	/**
	 * Creates a key for Entity Bean: Campo_rol
	 */
	public Campo_rolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey argFk_camrol_2_rol,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableKey argFk_camrol_2_cam) {
		privateSetFk_camrol_2_rolKey(argFk_camrol_2_rol);
		privateSetFk_camrol_2_camKey(argFk_camrol_2_cam);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Campo_rolKey) {
			co.com.telefonica.atiempo.ejb.eb.Campo_rolKey o =
				(co.com.telefonica.atiempo.ejb.eb.Campo_rolKey) otherKey;
			return (
				(this.fk_camrol_2_rol_rol_id.equals(o.fk_camrol_2_rol_rol_id))
					&& (this
						.fk_camrol_2_cam_cv_id
						.equals(o.fk_camrol_2_cam_cv_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_camrol_2_rol_rol_id.hashCode()
				+ fk_camrol_2_cam_cv_id.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_cam.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableKey getFk_camrol_2_camKey() {
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Campo_variableKey();
		boolean fk_camrol_2_cam_NULLTEST = true;
		fk_camrol_2_cam_NULLTEST &= (fk_camrol_2_cam_cv_id == null);
		temp.cv_id = fk_camrol_2_cam_cv_id;
		if (fk_camrol_2_cam_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_cam.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_camrol_2_camKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey inKey) {
		boolean fk_camrol_2_cam_NULLTEST = (inKey == null);
		fk_camrol_2_cam_cv_id = (fk_camrol_2_cam_NULLTEST) ? null : inKey.cv_id;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolKey getFk_camrol_2_rolKey() {
		co.com.telefonica.atiempo.ejb.eb.RolKey temp =
			new co.com.telefonica.atiempo.ejb.eb.RolKey();
		boolean fk_camrol_2_rol_NULLTEST = true;
		fk_camrol_2_rol_NULLTEST &= (fk_camrol_2_rol_rol_id == null);
		temp.rol_id = fk_camrol_2_rol_rol_id;
		if (fk_camrol_2_rol_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_camrol_2_rolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey inKey) {
		boolean fk_camrol_2_rol_NULLTEST = (inKey == null);
		fk_camrol_2_rol_rol_id =
			(fk_camrol_2_rol_NULLTEST) ? null : inKey.rol_id;
	}
}
