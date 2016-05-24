package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Excepcion_distribucion_carga_maxima
 */
public class Excepcion_distribucion_carga_maximaKey
	implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: exdcm_id
	 */
	public java.lang.Long exdcm_id;
	/**
	 * Creates an empty key for Entity Bean: Excepcion_distribucion_carga_maxima
	 */
	public Excepcion_distribucion_carga_maximaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Excepcion_distribucion_carga_maxima
	 */
	public Excepcion_distribucion_carga_maximaKey(java.lang.Long exdcm_id) {
		this.exdcm_id = exdcm_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Excepcion_distribucion_carga_maximaKey) {
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Excepcion_distribucion_carga_maximaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Excepcion_distribucion_carga_maximaKey) otherKey;
			return ((this.exdcm_id.equals(o.exdcm_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (exdcm_id.hashCode());
	}
}
