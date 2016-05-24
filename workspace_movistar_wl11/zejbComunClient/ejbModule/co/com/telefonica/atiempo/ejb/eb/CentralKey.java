package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Central
 */
public class CentralKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_central
	 */
	public java.lang.Long cod_central;
	/**
	 * Creates an empty key for Entity Bean: Central
	 */
	public CentralKey() {
	}
	/**
	 * Creates a key for Entity Bean: Central
	 */
	public CentralKey(java.lang.Long cod_central) {
		this.cod_central = cod_central;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.CentralKey) {
			co.com.telefonica.atiempo.ejb.eb.CentralKey o =
				(co.com.telefonica.atiempo.ejb.eb.CentralKey) otherKey;
			return ((this.cod_central.equals(o.cod_central)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_central.hashCode());
	}
}
