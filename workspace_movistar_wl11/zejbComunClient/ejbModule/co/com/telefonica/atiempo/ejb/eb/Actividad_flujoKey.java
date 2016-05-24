package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Actividad_flujo
 */
public class Actividad_flujoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: acti_id
	 */
	public java.lang.Integer acti_id;
	/**
	 * Creates an empty key for Entity Bean: Actividad_flujo
	 */
	public Actividad_flujoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Actividad_flujo
	 */
	public Actividad_flujoKey(java.lang.Integer acti_id) {
		this.acti_id = acti_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey) {
			co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey o =
				(co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey) otherKey;
			return ((this.acti_id.equals(o.acti_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (acti_id.hashCode());
	}
}
