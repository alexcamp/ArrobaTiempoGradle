package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Accion_masiva
 */
public class Accion_masivaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: acma_id
	 */
	public java.lang.Long acma_id;
	/**
	 * Creates an empty key for Entity Bean: Accion_masiva
	 */
	public Accion_masivaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Accion_masiva
	 */
	public Accion_masivaKey(java.lang.Long acma_id) {
		this.acma_id = acma_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Accion_masivaKey) {
			co.com.telefonica.atiempo.ejb.eb.Accion_masivaKey o =
				(co.com.telefonica.atiempo.ejb.eb.Accion_masivaKey) otherKey;
			return ((this.acma_id.equals(o.acma_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (acma_id.hashCode());
	}
}
