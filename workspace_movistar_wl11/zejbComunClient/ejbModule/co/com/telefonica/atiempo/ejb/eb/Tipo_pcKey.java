package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tipo_pc
 */
public class Tipo_pcKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_tipo_pc
	 */
	public java.lang.Integer id_tipo_pc;
	/**
	 * Creates an empty key for Entity Bean: Tipo_pc
	 */
	public Tipo_pcKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Tipo_pcKey) {
			co.com.telefonica.atiempo.ejb.eb.Tipo_pcKey o =
				(co.com.telefonica.atiempo.ejb.eb.Tipo_pcKey) otherKey;
			return ((this.id_tipo_pc.equals(o.id_tipo_pc)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_tipo_pc.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Tipo_pc
	 */
	public Tipo_pcKey(java.lang.Integer id_tipo_pc) {
		this.id_tipo_pc = id_tipo_pc;
	}
}
