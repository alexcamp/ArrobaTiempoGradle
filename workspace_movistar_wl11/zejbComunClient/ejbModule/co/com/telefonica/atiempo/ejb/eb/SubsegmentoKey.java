package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Subsegmento
 */
public class SubsegmentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: subsegm_id
	 */
	public java.lang.Long subsegm_id;
	/**
	 * Creates an empty key for Entity Bean: Subsegmento
	 */
	public SubsegmentoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey) {
			co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey o =
				(co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey) otherKey;
			return ((this.subsegm_id.equals(o.subsegm_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (subsegm_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Subsegmento
	 */
	public SubsegmentoKey(java.lang.Long subsegm_id) {
		this.subsegm_id = subsegm_id;
	}
}
