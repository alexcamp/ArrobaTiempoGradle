package co.com.telefonica.atiempo.vpistbba.serviciosba;
/**
 * Key class for Entity Bean: Mensaje_conf_ACS
 */
public class Mensaje_conf_ACSKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Integer id;
	/**
	 * Creates an empty key for Entity Bean: Mensaje_conf_ACS
	 */
	public Mensaje_conf_ACSKey() {
	}
	/**
	 * Creates a key for Entity Bean: Mensaje_conf_ACS
	 */
	public Mensaje_conf_ACSKey(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSKey) {
			co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSKey o = (co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSKey) otherKey;
			return ((this.id.equals(o.id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id.hashCode());
	}
}