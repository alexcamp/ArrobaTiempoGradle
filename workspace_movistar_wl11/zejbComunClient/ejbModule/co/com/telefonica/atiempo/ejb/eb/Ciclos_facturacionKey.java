package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Ciclos_facturacion
 */
public class Ciclos_facturacionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ciclo
	 */
	public java.lang.Integer ciclo;
	/**
	 * Creates an empty key for Entity Bean: Ciclos_facturacion
	 */
	public Ciclos_facturacionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Ciclos_facturacion
	 */
	public Ciclos_facturacionKey(java.lang.Integer ciclo) {
		this.ciclo = ciclo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionKey) {
			co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionKey o = (co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionKey) otherKey;
			return ((this.ciclo.equals(o.ciclo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ciclo.hashCode());
	}
}