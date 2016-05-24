package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Excepcion_carga_segmento
 */
public class Excepcion_carga_segmentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: excs_id
	 */
	public java.lang.Long excs_id;
	/**
	 * Creates an empty key for Entity Bean: Excepcion_carga_segmento
	 */
	public Excepcion_carga_segmentoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Excepcion_carga_segmento
	 */
	public Excepcion_carga_segmentoKey(java.lang.Long excs_id) {
		this.excs_id = excs_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoKey) {
			co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Excepcion_carga_segmentoKey) otherKey;
			return ((this.excs_id.equals(o.excs_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (excs_id.hashCode());
	}
}
