package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Ocupacion_rango
 */
public class Ocupacion_rangoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ocup_id
	 */
	public java.lang.Long ocup_id;
	/**
	 * Creates an empty key for Entity Bean: Ocupacion_rango
	 */
	public Ocupacion_rangoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Ocupacion_rango
	 */
	public Ocupacion_rangoKey(java.lang.Long ocup_id) {
		this.ocup_id = ocup_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoKey) {
			co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoKey o =
				(co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoKey) otherKey;
			return ((this.ocup_id.equals(o.ocup_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ocup_id.hashCode());
	}
}
