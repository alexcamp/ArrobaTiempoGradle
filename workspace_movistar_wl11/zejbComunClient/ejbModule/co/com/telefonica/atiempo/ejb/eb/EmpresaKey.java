package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Empresa
 */
public class EmpresaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: empr_id
	 */
	public java.lang.Long empr_id;
	/**
	 * Creates an empty key for Entity Bean: Empresa
	 */
	public EmpresaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.EmpresaKey) {
			co.com.telefonica.atiempo.ejb.eb.EmpresaKey o =
				(co.com.telefonica.atiempo.ejb.eb.EmpresaKey) otherKey;
			return ((this.empr_id.equals(o.empr_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (empr_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Empresa
	 */
	public EmpresaKey(java.lang.Long empr_id) {
		this.empr_id = empr_id;
	}
}
