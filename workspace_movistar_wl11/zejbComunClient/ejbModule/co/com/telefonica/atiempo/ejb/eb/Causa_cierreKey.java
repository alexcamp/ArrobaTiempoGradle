package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Causa_cierre
 */
public class Causa_cierreKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: caci_id
	 */
	public java.lang.Long caci_id;
	/**
	 * Creates an empty key for Entity Bean: Causa_cierre
	 */
	public Causa_cierreKey() {
	}
	/**
	 * Creates a key for Entity Bean: Causa_cierre
	 */
	public Causa_cierreKey(java.lang.Long caci_id) {
		this.caci_id = caci_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Causa_cierreKey) {
			co.com.telefonica.atiempo.ejb.eb.Causa_cierreKey o =
				(co.com.telefonica.atiempo.ejb.eb.Causa_cierreKey) otherKey;
			return ((this.caci_id.equals(o.caci_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (caci_id.hashCode());
	}
}
