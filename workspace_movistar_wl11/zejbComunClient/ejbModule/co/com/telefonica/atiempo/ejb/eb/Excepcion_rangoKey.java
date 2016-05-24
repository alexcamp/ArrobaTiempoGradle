package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Excepcion_rango
 */
public class Excepcion_rangoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: exra_id
	 */
	public java.lang.Long exra_id;
	/**
	 * Creates an empty key for Entity Bean: Excepcion_rango
	 */
	public Excepcion_rangoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Excepcion_rango
	 */
	public Excepcion_rangoKey(java.lang.Long exra_id) {
		this.exra_id = exra_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoKey) {
			co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoKey o =
				(co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoKey) otherKey;
			return ((this.exra_id.equals(o.exra_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (exra_id.hashCode());
	}
}
