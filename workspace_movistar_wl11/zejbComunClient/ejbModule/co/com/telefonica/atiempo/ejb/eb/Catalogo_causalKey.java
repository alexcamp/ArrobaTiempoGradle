package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Catalogo_causal
 */
public class Catalogo_causalKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_causal
	 */
	public java.lang.Long cod_causal;
	/**
	 * Creates an empty key for Entity Bean: Catalogo_causal
	 */
	public Catalogo_causalKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey) {
			co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey o =
				(co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey) otherKey;
			return ((this.cod_causal.equals(o.cod_causal)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_causal.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Catalogo_causal
	 */
	public Catalogo_causalKey(java.lang.Long cod_causal) {
		this.cod_causal = cod_causal;
	}
}
