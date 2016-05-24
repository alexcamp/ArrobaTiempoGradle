package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: DecoModemAdicionales
 */
public class DecoModemAdicionalesKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Long id;
	/**
	 * Returns true if both keys are equal. 
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.DecoModemAdicionalesKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.DecoModemAdicionalesKey o = (co.com.telefonica.atiempo.vpistbba.servicios.DecoModemAdicionalesKey) otherKey;
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
	/**
	 * Creates an empty key for Entity Bean: DecoModemAdicionales
	 */
	public DecoModemAdicionalesKey() {
	}
	/**
	 * Creates a key for Entity Bean: DecoModemAdicionales
	 */
	public DecoModemAdicionalesKey(java.lang.Long id) {
		this.id = id;
	}
}