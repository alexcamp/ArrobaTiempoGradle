package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Horario
 */
public class HorarioKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: hr_dia
	 */
	public java.lang.String hr_dia;
	/**
	 * Implementation field for persistent attribute: hr_tipo_semaforo
	 */
	public java.lang.Integer hr_tipo_semaforo;
	/**
	 * Implementation field for persistent attribute: aplicacion_ap_id
	 */
	public java.lang.Long aplicacion_ap_id;
	/**
	 * Implementation field for persistent attribute: rol_rol_id
	 */
	public java.lang.Long rol_rol_id;
	/**
	 * Creates an empty key for Entity Bean: Horario
	 */
	public HorarioKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.HorarioKey) {
			co.com.telefonica.atiempo.ejb.eb.HorarioKey o =
				(co.com.telefonica.atiempo.ejb.eb.HorarioKey) otherKey;
			return (
				(this.hr_dia.equals(o.hr_dia))
					&& (this.hr_tipo_semaforo.equals(o.hr_tipo_semaforo))
					&& (this.aplicacion_ap_id.equals(o.aplicacion_ap_id))
					&& (this.rol_rol_id.equals(o.rol_rol_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			hr_dia.hashCode()
				+ hr_tipo_semaforo.hashCode()
				+ aplicacion_ap_id.hashCode()
				+ rol_rol_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Horario
	 */
	public HorarioKey(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.AplicacionKey argAplicacion,
		co.com.telefonica.atiempo.ejb.eb.RolKey argRol) {
		this.hr_dia = hr_dia;
		this.hr_tipo_semaforo = hr_tipo_semaforo;
		privateSetAplicacionKey(argAplicacion);
		privateSetRolKey(argRol);
	}
	/**
	 * This method was generated for supporting the relationship role named aplicacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionKey getAplicacionKey() {
		co.com.telefonica.atiempo.ejb.eb.AplicacionKey temp =
			new co.com.telefonica.atiempo.ejb.eb.AplicacionKey();
		boolean aplicacion_NULLTEST = true;
		aplicacion_NULLTEST &= (aplicacion_ap_id == null);
		temp.ap_id = aplicacion_ap_id;
		if (aplicacion_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named aplicacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetAplicacionKey(
		co.com.telefonica.atiempo.ejb.eb.AplicacionKey inKey) {
		boolean aplicacion_NULLTEST = (inKey == null);
		aplicacion_ap_id = (aplicacion_NULLTEST) ? null : inKey.ap_id;
	}
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolKey getRolKey() {
		co.com.telefonica.atiempo.ejb.eb.RolKey temp =
			new co.com.telefonica.atiempo.ejb.eb.RolKey();
		boolean rol_NULLTEST = true;
		rol_NULLTEST &= (rol_rol_id == null);
		temp.rol_id = rol_rol_id;
		if (rol_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetRolKey(
		co.com.telefonica.atiempo.ejb.eb.RolKey inKey) {
		boolean rol_NULLTEST = (inKey == null);
		rol_rol_id = (rol_NULLTEST) ? null : inKey.rol_id;
	}
}
