package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Elemento
 */
public class ElementoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_elemento
	 */
	public java.lang.Integer id_elemento;
	/**
	 * Creates an empty key for Entity Bean: Elemento
	 */
	public ElementoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Elemento
	 */
	public ElementoKey(java.lang.Integer id_elemento) {
		this.id_elemento = id_elemento;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.ElementoKey) {
			co.com.telefonica.atiempo.ejb.eb.ElementoKey o = (co.com.telefonica.atiempo.ejb.eb.ElementoKey) otherKey;
			return ((this.id_elemento.equals(o.id_elemento)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_elemento.hashCode());
	}
}