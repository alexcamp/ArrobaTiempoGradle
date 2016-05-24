package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Causa_reagendamiento
 */
public class Causa_reagendamientoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: care_id
	 */
	public java.lang.Integer care_id;
	/**
	 * Creates an empty key for Entity Bean: Causa_reagendamiento
	 */
	public Causa_reagendamientoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Causa_reagendamiento
	 */
	public Causa_reagendamientoKey(java.lang.Integer care_id) {
		this.care_id = care_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoKey) {
			co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Causa_reagendamientoKey) otherKey;
			return ((this.care_id.equals(o.care_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (care_id.hashCode());
	}
}
