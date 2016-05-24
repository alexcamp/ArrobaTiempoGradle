package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Actividad
 */
public class ActividadKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: act_id
	 */
	public java.lang.Long act_id;
	/**
	 * Creates an empty key for Entity Bean: Actividad
	 */
	public ActividadKey() {
	}
	/**
	 * Creates a key for Entity Bean: Actividad
	 */
	public ActividadKey(java.lang.Long act_id) {
		this.act_id = act_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.ActividadKey) {
			co.com.telefonica.atiempo.ejb.eb.ActividadKey o =
				(co.com.telefonica.atiempo.ejb.eb.ActividadKey) otherKey;
			return ((this.act_id.equals(o.act_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (act_id.hashCode());
	}
}
