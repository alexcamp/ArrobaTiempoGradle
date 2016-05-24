package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Cierre_actividad
 */
public class Cierre_actividadKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ciac_id
	 */
	public java.lang.Long ciac_id;
	/**
	 * Creates an empty key for Entity Bean: Cierre_actividad
	 */
	public Cierre_actividadKey() {
	}
	/**
	 * Creates a key for Entity Bean: Cierre_actividad
	 */
	public Cierre_actividadKey(java.lang.Long ciac_id) {
		this.ciac_id = ciac_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey) {
			co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey o =
				(co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey) otherKey;
			return ((this.ciac_id.equals(o.ciac_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ciac_id.hashCode());
	}
}
