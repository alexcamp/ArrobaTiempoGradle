package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Instalaciones_VIP
 */
public class Instalaciones_VIPKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: atiempo
	 */
	public java.lang.Long atiempo;
	/**
	 * Creates an empty key for Entity Bean: Instalaciones_VIP
	 */
	public Instalaciones_VIPKey() {
	}
	/**
	 * Creates a key for Entity Bean: Instalaciones_VIP
	 */
	public Instalaciones_VIPKey(java.lang.Long atiempo) {
		this.atiempo = atiempo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPKey) {
			co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPKey o = (co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPKey) otherKey;
			return ((this.atiempo.equals(o.atiempo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (atiempo.hashCode());
	}
}