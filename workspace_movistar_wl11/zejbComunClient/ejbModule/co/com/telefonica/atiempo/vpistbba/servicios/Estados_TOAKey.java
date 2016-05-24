package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Estados_TOA
 */
public class Estados_TOAKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Integer correlativo;
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOAKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOAKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOAKey) otherKey;
			return ((this.correlativo.equals(o.correlativo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (correlativo.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Estados_TOA
	 */
	public Estados_TOAKey() {
	}
	/**
	 * Creates a key for Entity Bean: Estados_TOA
	 */
	public Estados_TOAKey(java.lang.Integer correlativo) {
		this.correlativo = correlativo;
	}
}