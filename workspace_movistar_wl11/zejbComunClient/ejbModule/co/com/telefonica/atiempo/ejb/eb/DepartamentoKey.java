package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Departamento
 */
public class DepartamentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_dpt
	 */
	public java.lang.String cod_dpt;
	/**
	 * Creates an empty key for Entity Bean: Departamento
	 */
	public DepartamentoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Departamento
	 */
	public DepartamentoKey(java.lang.String cod_dpt) {
		this.cod_dpt = cod_dpt;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.DepartamentoKey) {
			co.com.telefonica.atiempo.ejb.eb.DepartamentoKey o =
				(co.com.telefonica.atiempo.ejb.eb.DepartamentoKey) otherKey;
			return ((this.cod_dpt.equals(o.cod_dpt)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_dpt.hashCode());
	}
}
