package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Excepcion_carga_maxima
 */
public class Excepcion_carga_maximaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: excm_id
	 */
	public java.lang.Long excm_id;
	/**
	 * Creates an empty key for Entity Bean: Excepcion_carga_maxima
	 */
	public Excepcion_carga_maximaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Excepcion_carga_maxima
	 */
	public Excepcion_carga_maximaKey(java.lang.Long excm_id) {
		this.excm_id = excm_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_maximaKey) {
			co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_maximaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Excepcion_carga_maximaKey) otherKey;
			return ((this.excm_id.equals(o.excm_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (excm_id.hashCode());
	}
}
