package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Grupo_segmento
 */
public class Grupo_segmentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: grse_id
	 */
	public java.lang.Integer grse_id;
	/**
	 * Creates an empty key for Entity Bean: Grupo_segmento
	 */
	public Grupo_segmentoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Grupo_segmento
	 */
	public Grupo_segmentoKey(java.lang.Integer grse_id) {
		this.grse_id = grse_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey) {
			co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey o =
				(co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey) otherKey;
			return ((this.grse_id.equals(o.grse_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (grse_id.hashCode());
	}
}
