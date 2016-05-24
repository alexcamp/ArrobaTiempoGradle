package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Subtipo_pc
 */
public class Subtipo_pcKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_subtipo_pc
	 */
	public java.lang.Integer id_subtipo_pc;
	/**
	 * Creates an empty key for Entity Bean: Subtipo_pc
	 */
	public Subtipo_pcKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Subtipo_pcKey) {
			co.com.telefonica.atiempo.ejb.eb.Subtipo_pcKey o =
				(co.com.telefonica.atiempo.ejb.eb.Subtipo_pcKey) otherKey;
			return ((this.id_subtipo_pc.equals(o.id_subtipo_pc)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_subtipo_pc.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Subtipo_pc
	 */
	public Subtipo_pcKey(java.lang.Integer id_subtipo_pc) {
		this.id_subtipo_pc = id_subtipo_pc;
	}
}
