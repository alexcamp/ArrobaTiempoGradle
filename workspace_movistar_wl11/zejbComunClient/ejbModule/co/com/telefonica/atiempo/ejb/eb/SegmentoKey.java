package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Segmento
 */
public class SegmentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: segm_id
	 */
	public java.lang.Long segm_id;
	/**
	 * Creates an empty key for Entity Bean: Segmento
	 */
	public SegmentoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Segmento
	 */
	public SegmentoKey(java.lang.Long segm_id) {
		this.segm_id = segm_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.SegmentoKey) {
			co.com.telefonica.atiempo.ejb.eb.SegmentoKey o =
				(co.com.telefonica.atiempo.ejb.eb.SegmentoKey) otherKey;
			return ((this.segm_id.equals(o.segm_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (segm_id.hashCode());
	}
}
