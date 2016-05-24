package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Limite
 */
public class LimiteKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: li_tipo_semaforo
	 */
	public java.lang.Integer li_tipo_semaforo;
	/**
	 * Implementation field for persistent attribute: actividad_act_id
	 */
	public java.lang.Long actividad_act_id;
	/**
	 * Creates an empty key for Entity Bean: Limite
	 */
	public LimiteKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.LimiteKey) {
			co.com.telefonica.atiempo.ejb.eb.LimiteKey o =
				(co.com.telefonica.atiempo.ejb.eb.LimiteKey) otherKey;
			return (
				(this.li_tipo_semaforo.equals(o.li_tipo_semaforo))
					&& (this.actividad_act_id.equals(o.actividad_act_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (li_tipo_semaforo.hashCode() + actividad_act_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Limite
	 */
	public LimiteKey(
		java.lang.Integer li_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.ActividadKey argActividad) {
		this.li_tipo_semaforo = li_tipo_semaforo;
		privateSetActividadKey(argActividad);
	}
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadKey getActividadKey() {
		co.com.telefonica.atiempo.ejb.eb.ActividadKey temp =
			new co.com.telefonica.atiempo.ejb.eb.ActividadKey();
		boolean actividad_NULLTEST = true;
		actividad_NULLTEST &= (actividad_act_id == null);
		temp.act_id = actividad_act_id;
		if (actividad_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetActividadKey(
		co.com.telefonica.atiempo.ejb.eb.ActividadKey inKey) {
		boolean actividad_NULLTEST = (inKey == null);
		actividad_act_id = (actividad_NULLTEST) ? null : inKey.act_id;
	}
}
