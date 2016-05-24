package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Grupos_Especiales
 */
public class Grupos_EspecialesKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: grpe_id
	 */
	public java.lang.Long grpe_id;
	/**
	 * Creates an empty key for Entity Bean: Grupos_Especiales
	 */
	public Grupos_EspecialesKey() {
	}
	/**
	 * Creates a key for Entity Bean: Grupos_Especiales
	 */
	public Grupos_EspecialesKey(java.lang.Long grpe_id) {
		this.grpe_id = grpe_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Grupos_EspecialesKey) {
			co.com.telefonica.atiempo.ejb.eb.Grupos_EspecialesKey o = (co.com.telefonica.atiempo.ejb.eb.Grupos_EspecialesKey) otherKey;
			return ((this.grpe_id.equals(o.grpe_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (grpe_id.hashCode());
	}
}