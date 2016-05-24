package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Activaciones_tv_TOA
 */
public class Activaciones_tv_TOAKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Activaciones_tv_TOA
	 */
	public Activaciones_tv_TOAKey() {
	}
	/**
	 * Creates a key for Entity Bean: Activaciones_tv_TOA
	 */
	public Activaciones_tv_TOAKey(java.lang.Long correlativo) {
		this.correlativo = correlativo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOAKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOAKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOAKey) otherKey;
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
}